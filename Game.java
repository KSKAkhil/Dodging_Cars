import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.net.URL;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Game extends JPanel{
    
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
    
    public Game(){
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
        JFrame frame = new JFrame("Car Racing Game");   
        Game game = new Game();
        frame.add(game);		
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
            }
        }
    }
}
