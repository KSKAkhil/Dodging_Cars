import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.net.URL;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
class A
	{
	public static void main(String []args)
		{
		JFrame f=new JFrame("Maze Game");
		f.setSize(1000,1000);
		f.setVisible(true);
		Font f1=new Font("Sans Serif",Font.BOLD,80);
		Font f2=new Font("Sans Serif",Font.BOLD,45);
		JLabel l=new JLabel("The Maze");
		l.setFont(f1);
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		l.setBounds(500,30,400,200);
		f.add(l);
		f.setLayout(null);
		JButton b1=new JButton("Start");
		JButton b2=new JButton("Settings");
		JButton b3=new JButton("Exit");
		b1.setBounds(550,200,300,60);
		b2.setBounds(550,300,300,60);
		b3.setBounds(550,400,300,60);
		b1.setFont(f2);
		b2.setFont(f2);
		b3.setFont(f2);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		b1.addMouseListener(new B());
		b2.addActionListener(new C());
		b3.addActionListener(new D());
		}
	
	}
class B implements MouseListener
		{
		public void mouseClicked(MouseEvent e)
			{
			/*JFrame f2=new JFrame("New Frame");
			f2.setSize(400,400);
			f2.setVisible(true);
			f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
			Game g=new Game();
			g.setVisible(true);
			g.setSize(200,200);
			
			}
    public void mouseEntered(MouseEvent e) {  
        //l.setText("Mouse Entered");  
    }  
    public void mouseExited(MouseEvent e) {  
       // l.setText("Mouse Exited");  
    }  
    public void mousePressed(MouseEvent e) {  
       // l.setText("Mouse Pressed");  
    }  
    public void mouseReleased(MouseEvent e) {  
       // l.setText("Mouse Released");  
    }  
			
		}
class C implements ActionListener
		{
		public void actionPerformed(ActionEvent e)
			{
			JFrame f3=new JFrame("Settings Frame");
			f3.setLayout(new FlowLayout());
			f3.setSize(400,400);
			Font f1=new Font("Sans Serif",Font.BOLD,30);
			JLabel l1=new JLabel("Sound");
			l1.setBounds(150,10,400,200);
			l1.setFont(f1);
			JLabel l2=new JLabel("Brightness");
			l2.setBounds(150,10,400,200);
			l2.setFont(f1);
			JSlider s=new JSlider(JSlider.HORIZONTAL, 0, 300, 25);
			s.setBounds(200,100,0,300);
			f3.add(l1);
			JSlider s1=new JSlider(JSlider.HORIZONTAL, 0, 300, 25);
			s1.setBounds(200,100,0,300);
			f3.add(s); 
			f3.add(l2);
			f3.add(s1);
			f3.setVisible(true);
			f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f3.setResizable(false);
			}
		}
class D implements ActionListener
		{
		public void actionPerformed(ActionEvent e)
			{
			System.exit(0);
			}
		}
////////////////////////////////////////////////////////////////////////////////////////////
class Game extends JPanel{
    
    int crx,cry;	
    int car_x,car_y;   
    int speedX,speedY;	
    int nOpponent;     
    String imageLoc[]; 
    int lx[],ly[];  
    int score;      
    int highScore;  
    int speedOpponent[]; 
    boolean isFinished; 
    boolean isUp, isDown, isRight, isLeft;  
    JFrame frame;
     Game(){
	 frame = new JFrame("Car Racing Game"); 
frame.setSize(500,500); 
        frame.setVisible(true); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int count = 1, c = 1;
        while(true){
            game.moveRoad(count);  
            while(c <= 1){
                game.repaint();     
                try{
                    Thread.sleep(5);   
                }
                catch(Exception e){
                    System.out.println(e);
                }
                c++;
            }
            c = 1;
            count++; //increment count value
            if(game.nOpponent < 4 && count % 200 == 0){ 
                game.imageLoc[game.nOpponent] = "images/car_left_"+((int)((Math.random()*100)%3)+1)+".png"; 
                game.lx[game.nOpponent] = 499; 
                int p = (int)(Math.random()*100)%4;
                if(p == 0){     
                    p = 250;    
                }
                else if(p == 1){ 
                    p = 300;    
                }
                else if(p == 2){ 
                    p = 185;   
                }
                else{           
                    p = 130;    
                }
                game.ly[game.nOpponent] = p; 
                game.speedOpponent[game.nOpponent] = (int)(Math.random()*100)%4 + 3; 
                game.nOpponent++; 
        crx = cry = -999;   
        
        addKeyListener(new KeyListener() { 
            public void keyTyped(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) { 
                stopCar(e); 
            }
            public void keyPressed(KeyEvent e) { 
                moveCar(e); 
            }
        });
        setFocusable(true); 
        car_x = 0;  
	car_y = 220;  
        isUp = isDown = isLeft = isRight = false;  
        speedX =  1;  
	speedY = 0;  
        nOpponent = 0;  
        lx = new int[20]; 
        ly = new int[20]; 
        imageLoc = new String[20];
        speedOpponent = new int[20]; 
        isFinished = false;
        score = highScore = 0;  
    }
    }
  
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D obj = (Graphics2D) g;
        obj.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        try{
            obj.drawImage(getToolkit().getImage("images/st_road.png"), 0, 0 ,this); 
            if(cry >= -499 && crx >= -499) //if a road crossing has passed the window view
                obj.drawImage(getToolkit().getImage("images/cross_road.png"),crx,cry,this); 
            
            obj.drawImage(getToolkit().getImage("images/lambo.png"),car_x,car_y,this);   
            
            if(isFinished){ //if collision occurs
                obj.drawImage(getToolkit().getImage("images/boom.png"),car_x-30,car_y-30,this); 
            }
            
            if(this.nOpponent > 0){ 
                for(int i=0;i<this.nOpponent;i++){ //for every opponent car
                    obj.drawImage(getToolkit().getImage(this.imageLoc[i]),this.lx[i],this.ly[i],this); 
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    //function that moves the road scene across the window to make it seem like the car is driving
    void moveRoad(int count){
        if(crx == -999 && cry == -999){ 
            if(count%10 == 0){ 
                crx = 499;      
                cry = 0;
            }
        }
        else{  
            crx--; 
        }
        if(crx == -499 && cry == 0){ 
            crx = cry = -999;  
        }
        car_x += speedX; 
        car_y += speedY; 
        
        
        if(car_x < 0)   
            car_x = 0;  
        
        
        if(car_x+93 >= 500)
            car_x = 500-93; 
        
        
        if(car_y <= 124)    
            car_y = 124;   
        
       
        if(car_y >= 364-50) 
            car_y = 364-50;
        
        
        for(int i=0;i<this.nOpponent;i++){
            this.lx[i] -= speedOpponent[i]; 
        }
        
        
        int index[] = new int[nOpponent];
        for(int i=0;i<nOpponent;i++){
            if(lx[i] >= -127){
                index[i] = 1;
            }
        }
        int c = 0;
        for(int i=0;i<nOpponent;i++){
            if(index[i] == 1){
                imageLoc[c] = imageLoc[i];
                lx[c] = lx[i];
                ly[c] = ly[i];
                speedOpponent[c] = speedOpponent[i];
                c++;
            }
        }
        
        score += nOpponent - c; 
        
        if(score > highScore)   
            highScore = score;  
        
        nOpponent = c;
        
        //Check for collision
        int diff = 0; 
        for(int i=0;i<nOpponent;i++){ 
            diff = car_y - ly[i]; 
            if((ly[i] >= car_y && ly[i] <= car_y+46) || (ly[i]+46 >= car_y && ly[i]+46 <= car_y+46)){ 
                if(car_x+87 >= lx[i] && !(car_x >= lx[i]+87)){  
                    System.out.println("Game over..");
                    this.finish(); 
                }
            }
        }
    }
    
  
    void finish(){
        String str = "";   
        isFinished = true;  
        this.repaint();    
        if(score == highScore && score != 0) 
            str = "\nCongratulations!!! Its a high score";  
        JOptionPane.showMessageDialog(this,"Game Over!!!\nYour Score : "+score+"\nHigh Score : "+highScore+str,     "Game Over", JOptionPane.YES_NO_OPTION);    
        System.exit(ABORT); 
    }
    
    
   
    public void moveCar(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W){   
            isUp = true;
            speedX = 3;     
        }
        if(e.getKeyCode() == KeyEvent.VK_S){ 
            isDown = true;
            speedX = -3;   
        }
        if(e.getKeyCode() == KeyEvent.VK_D){ 
            isRight = true;
            speedY = 3;     
        }
        if(e.getKeyCode() == KeyEvent.VK_A){ 
            isLeft = true;
            speedY = -3;    
        }
    }
    
   
    public void stopCar(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W){  
            isUp = false;
            speedX = 0; 
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){    
            isDown = false;
            speedX = 0; 
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){    
            isLeft = false;
            speedY = 0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){   
            isRight = false;
            speedY = 0; 
        }
    }
    
    //main method where the java application begins processing
    public static void main(String args[]){
        //JFrame frame = new JFrame("Car Racing Game");   
        Game game = new Game();
        frame.add(game);		
       
            }
        
    
}
