package mapGames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

//Starting class for MapContinent program

public class MapContinent {

	// constants
	final static int GRID = 128; // size of grid/board (at higher values the timer begins to lag due to timer using too much cpu)
	int SQSIZE = 512/GRID; // size of each square in pixels
	final static int SPACING = 0;
	final static int NUM_LAND = (GRID * GRID / 2); // number of land tiles
	final static boolean USETIMER = true; // Disables animations
	final static boolean IGNOREEXCEPTION = false; // Game may not work due to game being bigger than monitor resolution
	final static boolean DEBUG = true; // saves an image of the noise generation

	// terrain
	final static int EMPTY = 0; // constant for empty tile. This is the terrain that needs to be a specific
								// value (since arrays are initialized to zero)
	final static int LAND = 1; // contant for land tile
	final static int LAKE = 33; // this is just any number used for LAKE and OCEAN
	final static int OCEAN = 89;
	// colours: you can change these
	final static Color COLOURBACK = new Color(242, 242, 242);
	final static Color COLOUREMPTY = new Color(222, 222, 222);
	final static Color COLOURLAND = new Color(100, 200, 100);
	final static Color COLOURLAKE = new Color(100, 100, 255);
	final static Color COLOUROCEAN = new Color(10, 10, 130);
	final static double REQHEIGHT = 4;

	// global variables
	int[][] board = new int[GRID][GRID];
	double avgHeight = 0.00;
	JFrame frame = new JFrame("Mouse1: Place Water, Mouse2: Land, ScrollClick: New Map, N: Switch Generation Type, S: Save, L: Load");
	boolean generation = true; // false = random, true = continents
	int brushSize = 25;
	boolean debounce = false;
	
	public static void main(String[] args) throws IOException {
		if (GRID > 512 && !IGNOREEXCEPTION) {
			throw new IOException("Grid cannot be bigger than 512!");   
		}
		new MapContinent();
	}
	
	MapContinent() { // constructor
		if (GRID > 512 && IGNOREEXCEPTION) {
			SQSIZE = 1;
		}
		initGame();
		createAndShowGUI();
	}

	// PROBLEM 4: When half of the squares are land, the land is scattered quite a
	// lot into little islands.
	// Find a way to make a random map that has the land in bigger chunks.
	void initGame() {
		// clear board
		for (int i = 0; i < GRID; i++) {
			for (int j = 0; j < GRID; j++) {
				board[i][j] = EMPTY;
			}
		}
		
		if (!generation) 
			makeRandomMap();
		else
			makeContinents();
	}
	

	void makeRandomMap() {
		int i, j;
		i = j = 0;
		boolean done = false;
		int landTiles = 0;
		// PROBLEM 1: Make an equal number of land and water squares, but make sure that
		// the land is randomly distributed.
		for (int t2 = 0; t2 < NUM_LAND; t2++) {
			i = (int) (Math.random() * GRID);
			j = (int) (Math.random() * GRID);
			if (board[i][j] == EMPTY) {
				board[i][j] = LAND;
			} else {
				t2--;
			}
		}

	}
	
	void makeContinents() {
		NoiseGenerator noise = new NoiseGenerator();
		
		for (int y = 0; y < GRID; y++) {
			for (int x = 0; x < GRID; x++) {
				double value = noise.noise(x, y);
				avgHeight += value;
			}
		}
		avgHeight = (avgHeight / (GRID * GRID))*((REQHEIGHT*10)/SQSIZE);
		if (DEBUG) {
			debugPerlinNoise(noise);
		}
		System.out.println(avgHeight);
		for (int y = 0; y < GRID; y++) {
			for (int x = 0; x < GRID; x++) {
				double value = noise.noise(x, y);
				if (avgHeight < 0) {
					if (value > avgHeight) {
						board[x][y] = LAND;
					}
				} else {
					if (value < avgHeight) {
						board[x][y] = LAND;
					}
				}
			}
		}
		avgHeight = 0.00;
	}
	
	void debugPerlinNoise(NoiseGenerator noise) {
		BufferedImage image = new BufferedImage(GRID, GRID, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < GRID; y++)
		{
			for (int x = 0; x < GRID; x++)
			{
				double value = noise.noise(x , y );
				double colour = ((value + 1)*127)%255;
				int rgb = 0;
				if (colour < 0) {
					colour = -colour;
				}
				if (avgHeight < 0) {
					if (value > avgHeight) {
						rgb = new Color(1, (int) (colour), (int) (colour)).getRGB();
					} else {
						colour = 255-colour;
						rgb = new Color((int) (colour), (int) (colour), (int) (colour)).getRGB();
					}
				} else {
					if (value < avgHeight) {
						colour = 255-colour;
						rgb = new Color(1, (int) (colour), (int) (colour)).getRGB();
					} else {
						rgb = new Color((int) (colour), (int) (colour), (int) (colour)).getRGB();
					}
				}
				
				//int rgb = 0x010101 * (int)((value + 1) * 127.5);
				image.setRGB(x, y, rgb);
			}
		}
		try {
			ImageIO.write(image, "png", new File("noise.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void createAndShowGUI() {
		DrawingPanel panel = new DrawingPanel();

		// JFrame.setDefaultLookAndFeelDecorated(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = frame.getContentPane();
		// content.setLayout(new BorderLayout(2,2));
		content.add(panel, BorderLayout.CENTER);
		// frame.setSize(SCRSIZE, SCRSIZE); //may not be needed since my JPanel has a
		// preferred size
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// once the panel is visible, initialize the graphics. (Is this before
		// paintComponent is run?)
		panel.initGraphics();

	}

	class DrawingPanel extends JPanel // inner class
	{
		int jpanW, jpanH;
		int blockX, blockY;

		public DrawingPanel() {
			setBackground(COLOURBACK);
			// Because the panel size variables don't get initialized until the panel is
			// displayed,
			// we can't do a lot of graphics initialization here in the constructor.
			this.setPreferredSize(new Dimension(GRID * SQSIZE, GRID * SQSIZE));
			MyMouseListener ml = new MyMouseListener();
			addMouseListener(ml);
			MyKeyListener k1 = new MyKeyListener();
			frame.addKeyListener(k1);
			
		}

		// ** Called by createGUI()
		void initGraphics() {
			jpanW = this.getSize().width;
			jpanH = this.getSize().height;
			blockX = (int) ((jpanW / GRID) + 0.5);
			blockY = (int) ((jpanH / GRID) + 0.5);
			// System.out.println("init");
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Draw white grid
			g.setColor(Color.WHITE);
			for (int i = 0; i < GRID; i++) {
				g.drawLine(blockX * i, 0, blockX * i, jpanH);
				g.drawLine(0, blockY * i, jpanW, blockY * i);
			}

			for (int i = 0; i < GRID; i++) {
				for (int j = 0; j < GRID; j++) {
					colourRect(i, j, g);
				}
			}
		}

		void colourRect(int i, int j, Graphics g) {

			int terrain = board[i][j];

			if (terrain == EMPTY) {
				g.setColor(COLOUREMPTY);
				g.fillRect(blockX * i, blockY * j, blockX - SPACING, blockY - SPACING);
			}
			if (terrain == LAND) {
				g.setColor(COLOURLAND);
				g.fillRect(blockX * i, blockY * j, blockX - SPACING, blockY - SPACING);
			}
			if (terrain == LAKE) {
				g.setColor(COLOURLAKE);
				g.fillRect(blockX * i, blockY * j, blockX - SPACING, blockY - SPACING);
			}
			if (terrain == OCEAN) {
				g.setColor(COLOUROCEAN);
				g.fillRect(blockX * i, blockY * j, blockX - SPACING, blockY - SPACING);
			}
		}

		// PROBLEM 2: Fix the function "findLakes()" so that it colours all empty
		// squares that are adjacent to this one.
		// PROBLEM 3: Once you have solved problem 2, now set things up so that if any
		// part
		// of a lake touches the edge of the board it becomes an ocean.
		void findLakes(int x, int y, boolean rClick, int type) {
			// call subroutine to colour in all contiguous lake squares
			if (debounce)
				return;
			if (x < 0 || x >= GRID || y < 0 || y >= GRID)
				return;
			if (board[x][y] == OCEAN && !rClick && type == LAKE) {
				//findLakes(x, y, false, OCEAN);
				return;
			}
			if (board[x][y] != EMPTY && !rClick && type == LAKE)
				return;
			if ((x == 0 || y == 0 || x == GRID - 1 || y == GRID - 1) && !rClick && type == LAKE) {
				findLakes(x, y, false, OCEAN);
				return;
			}
			if (board[x][y] != LAKE && board[x][y] != EMPTY && type == OCEAN)
				return;
			if (!rClick)
				board[x][y] = type;
			
			if (rClick) {
				ActionListener task = new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	if (board[x + 1][y] == OCEAN || board[x - 1][y] == OCEAN || board[x][y + 1] == OCEAN || board[x][y - 1] == OCEAN)
		            		findLakes(x, y, false, OCEAN);
		            	else if (board[x + 1][y] == LAKE || board[x - 1][y] == LAKE || board[x][y + 1] == LAKE || board[x][y - 1] == LAKE)
		            		findLakes(x, y, false, LAKE);
						
		            }
		        };
		        Timer timer = new Timer((int) (GRID*0.1), task);
		        timer.setRepeats(false);
		        timer.start();
		        repaint();
		        return;
			} else {
				if (USETIMER) {
					ActionListener task = new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	if (x+1 < GRID && (board[x+1][y] != type))
								findLakes(x + 1, y, false, type);
							if (x-1 >= 0 && (board[x-1][y] != type))
								findLakes(x - 1, y, false, type);
							if (y+1 < GRID && (board[x][y+1] != type))
								findLakes(x, y + 1, false, type);
							if (y-1 >= 0 && (board[x][y-1] != type))
								findLakes(x, y - 1, false, type);
							repaint();
			            }
			        };
			        Timer timer = new Timer((int) (800/GRID), task);
			        timer.setRepeats(false);
			        timer.start();
			        return;
		        } else {
		        	findLakes(x + 1, y, false, type);
	            	findLakes(x - 1, y, false, type);
	            	findLakes(x, y + 1, false, type);
	            	findLakes(x, y - 1, false, type);
		        }
			}
			repaint();
			/*
			 * new java.util.Timer().schedule(new java.util.TimerTask() {
			 * @Override public void run() { findLakes(x + 1, y); findLakes(x - 1, y);
			 * findLakes(x, y + 1); findLakes(x, y - 1); repaint(); } }, 50);
			 */
		}

		class MyMouseListener extends MouseAdapter { // inner class inside DrawingPanel
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// calculate which square you clicked on
				int i = (int) x / blockX;
				int j = (int) y / blockY; // blockY/y
				

				// allow the right mouse button to toggle/cycle the terrain
				if (e.getButton() == MouseEvent.BUTTON3) {
					/*int tiletype = 0;
					boolean checked = false;
					for (int row = 0; row < brushSize; row++) {
						if (row+(i-brushSize/2) < GRID && row+(i-brushSize/2) > 0) {
							for (int col = 0; col < brushSize; col++) {
								if (col+(j-brushSize/2) < GRID && col+(j-brushSize/2) > 0) {
									double d = Math.sqrt((brushSize/2 - row) * (brushSize/2 - row) + (brushSize/2 - col) * (brushSize/2 - col));
									if (d <= brushSize/2) {
										if (!checked) {
											checked = true;
											tiletype = board[i][j];
										}
										switch (tiletype) {
										case LAND:
											board[row+(i-brushSize/2)][col+(j-brushSize/2)] = EMPTY;
											break;
										default:
											board[row+(i-brushSize/2)][col+(j-brushSize/2)] = LAND;
										}
									}
								}
							}
						}Sphere brush (didn't finish too much work)*/
						switch (board[i][j]) {
						case LAND:
							board[i][j] = EMPTY;
							findLakes(i,j, true, LAKE);
							break;
						default:
							board[i][j] = LAND;
						}
						repaint();
						return;
					}
					/*switch (board[i][j]) {
					case LAND:
						board[i][j] = EMPTY;
						findLakes(i,j, true, LAKE);
						break;
					default:
						board[i][j] = LAND;
					}
					repaint();
					return;*/
				if (e.getButton() == MouseEvent.BUTTON2) {
					initGame();
					repaint();
					debounce = true;
					ActionListener task = new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	debounce = false;
							
			            }
			        };
			        Timer timer = new Timer((int) (100), task);
			        timer.setRepeats(false);
			        timer.start();
			        repaint();
					return;
				}
				findLakes(i, j, false, LAKE);
				// repaint();
			}
		} // end of MyMouseListener class
		
		class MyKeyListener implements KeyListener {
			@Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyChar() == 'n') {
	            	generation = !generation;
	            	debounce = true;
					ActionListener task = new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	debounce = false;
							
			            }
			        };
			        Timer timer = new Timer((int) (100), task);
			        timer.setRepeats(false);
			        timer.start();
			        repaint();
	            	initGame();
	            	repaint();
	            }
	            if (e.getKeyChar() == 's') {
	            	BufferedImage image = new BufferedImage(GRID, GRID, BufferedImage.TYPE_INT_RGB);
					for (int y = 0; y < GRID; y++)
					{
						for (int x = 0; x < GRID; x++)
						{
							int rgb = 0;
							if (board[x][y] == EMPTY) {
								rgb = COLOUREMPTY.getRGB();
							}
							if (board[x][y] == LAND) {
								rgb = COLOURLAND.getRGB();
							}
							if (board[x][y] == LAKE) {
								rgb = COLOURLAKE.getRGB();
							}
							if (board[x][y] == OCEAN) {
								rgb = COLOUROCEAN.getRGB();
							}
							
							//int rgb = 0x010101 * (int)((value + 1) * 127.5);
							image.setRGB(x, y, rgb);
						}
					}
					try {
						ImageIO.write(image, "png", new File("map.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	            if (e.getKeyChar() == 'l') {
	            	BufferedImage image;
	            	debounce = true;
	            	ActionListener task = new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	debounce = false;
							
			            }
			        };
			        Timer timer = new Timer((int) (100), task);
			        timer.setRepeats(false);
			        timer.start();
					try {
						image = ImageIO.read(new File("map.png"));
						for (int y = 0; y < GRID; y++)
						{
							for (int x = 0; x < GRID; x++)
							{	
								int rgb = 0;
								if (GRID>image.getWidth()) {
									rgb = image.getRGB((int) (x/(GRID/image.getWidth())),(int) (y/(GRID/image.getWidth())));
								} else if (GRID<image.getWidth()) {
									// Down Sampling bigger image
									rgb = image.getRGB((int) (((image.getWidth()/GRID)*(x))),(int) (((image.getWidth()/GRID)*(y))));
								} else {
									rgb = image.getRGB(x, y);
								}
								
								if (rgb == COLOUREMPTY.getRGB()) {
									board[x][y] = EMPTY;
								}
								if (rgb == COLOURLAND.getRGB()) {
									board[x][y] = LAND;
								}
								if (rgb == COLOURLAKE.getRGB()) {
									board[x][y] = LAKE;
								}
								if (rgb == COLOUROCEAN.getRGB()) {
									board[x][y] = OCEAN;
								}
								
								//int rgb = 0x010101 * (int)((value + 1) * 127.5);
								
							}
						}
						repaint();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
	            }
	        }

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		}

	} // end of DrawingPanel class

}