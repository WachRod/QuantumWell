/* File:mainApp.java
* Project :Potential Well Simulation
* Author  : Wachara R.
* First Released: 19 Jan 2011
* Last Updated : 
*/

package se1d;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SE1DApp extends JFrame
{
  private String title;
 private SEPanel displayPanel;

    /**
     * Constructor.
     */
private SE1DApp( String title, SEPanel displayPanel)
    {
        this.title = title;
		this.displayPanel = displayPanel;
		setTitle(title);
		initFrame();
    }
    /**
     * Initialize the frame.
     */
private void initFrame()
    {
        // Center the current frame.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width*9/10;;
		int height = screenSize.height*9/10;
		 setSize(width, height);
      setLocation((screenSize.width  - width )/2,
                  (screenSize.height - height)/2);
	// ---------------- full screen version------------
	//	setSize(screenSize.width,screenSize.height);
	//	setLocation(0,0);
	//-------------------------------------------------------
        setLayout(new BorderLayout());
        add(displayPanel, BorderLayout.CENTER);

        // Initialize the demo.
   //     displayPanel.panelInitialized();

        // Window event handlers.
/*
        addWindowListener(new WindowAdapter()
        {
            public void windowOpened(WindowEvent ev)
            {
                repaint();
            }


        });
*/
        // Resize event handler.
        addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent ev)
            {
             //  displayPanel.panelResized();
            }
        });

    }

    /**
     * Update the display without repainting the background.
     * @param g the graphics context
     */
    public void update(Graphics g)
    {
      //  displayPanel.panelDraw();
    }
    /**
     * Main.
     */
    public static void main(String args[])
    {

		  EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
				 JFrame frame = new SE1DApp("RMUTT Physics ", new SEPanel());

               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
			   frame.setResizable(false);
            }
         });
    }
}
