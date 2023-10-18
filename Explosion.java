import greenfoot.*;

public class Explosion extends Actor
{
    private final static int IMAGE_COUNT= 12;
    
    private static GreenfootImage[] images;
    
    private int imageNo = 0;
    
    private int increment=1;
    
    /**
     * Create a new explosion.
     */
    public Explosion() 
    {
        initializeImages();
        setImage(images[0]);
        Greenfoot.playSound("MetalExplosion.wav");
    }    
    
    /** 
     * Create the images for explosion.
     */
    public synchronized static void initializeImages() 
    {
        if(images == null) 
        {
            GreenfootImage baseImage = new GreenfootImage("explosion-big.png");
            images = new GreenfootImage[IMAGE_COUNT];
            for (int i = 0; i < IMAGE_COUNT; i++)
            {
                int size = (i+1) * ( baseImage.getWidth() / IMAGE_COUNT );
                images[i] = new GreenfootImage(baseImage);
                images[i].scale(size, size);
            }
        }
    }
    
    /**
     * Explode!
     */
    public void act()
    { 
        setImage(images[imageNo]);

        imageNo += increment;
        if(imageNo >= IMAGE_COUNT) 
        {
            increment = -increment;
            imageNo += increment;
        }
        
        if(imageNo < 0) 
        {
            getWorld().removeObject(this);
        }
    }
}