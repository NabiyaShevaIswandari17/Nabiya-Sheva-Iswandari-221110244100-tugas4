import greenfoot.*;
import java.util.List;

public class ProtonWave extends Actor
{
    private static final int DAMAGE = 30;
    
    private static final int NUMBER_IMAGES= 30;

    private static GreenfootImage[] images;

    private int currentImage = 0;
    
    /**
     * Create a new proton wave.
     */
    public ProtonWave() 
    {
        initializeImages();
        setImage(images[0]);
        Greenfoot.playSound("proton.wav");
    }
    
    /** 
     * Create the images for expanding the wave.
     */
    public static void initializeImages() 
    {
        if (images == null) 
        {
            GreenfootImage baseImage = new GreenfootImage("wave.png");
            images = new GreenfootImage[NUMBER_IMAGES];
            for (int i = 0; i < NUMBER_IMAGES; i++) 
            {
                int size = (i+1) * ( baseImage.getWidth() / NUMBER_IMAGES );
                images[i] = new GreenfootImage(baseImage);
                images[i].scale(size, size);
            }
        }
    }
    
    /**
     * Act for the proton wave is: grow and check whether we hit anything.
     */
    public void act()
    { 
        checkCollision();
        grow();
    }
    
    /**
     * Explode all intersecting asteroids.
     */
    private void checkCollision()
    {
        int range = getImage().getWidth() / 2;
        List<Asteroid> asteroids = getObjectsInRange(range, Asteroid.class);     
        
        for (Asteroid a : asteroids) 
        {
            a.hit (DAMAGE);
        }
    }

    /**
     * Grow this wave. If we get to full size, remove.
     */
    private void grow()
    {
        if (currentImage >= NUMBER_IMAGES) 
        {
            getWorld().removeObject(this);
        }
        else 
        {
            setImage(images[currentImage]);
            currentImage++;
        }
    }
}
