# GridEngine v0.72.0 - Once files are uploaded, version will be set.

---

This is a 2D engine developed for ease of use. The idea behind the engine itself is to allow 'grid-like' snapping for player position, but not require it.
- There will still be fluid motion available for use.

---

## Documentation

**Runner.java:**

- Main file for the engine.

##class Runner:

    + __*start()*__
        - Start the engine
        - Initializes required elements of the engine, such as entity storage
       
    + __*stop()*__
        - Stop the engine
        - Should only be called on engine termination
        
    + __*init()*__
        - Initializes some variables
        - Currently a public method, will be converted to private or protected based on future needs.
        
    + __*tick()*__
        - Called within the 'Run' function required from 'implements Runnable' of Runner class
        - Currently a public method, will be converted to private or protected based on future needs.
        
    + __*render()*__
        - Called within the 'Run' function required from 'implements Runnable' of Runner class
        - Currently a public method, will be converted to private or protected based on future needs.
        
    + __*run()*__
        - Required for the engine to run, required from the implemented "Runnable". Should **NOT** be called manually.
        
**Screen.java:**

- Class used to handle all rendering elements. Stores an array of pixels to be read/cleared by Runner.java

## class DrawComparator

    + __*compare(Sprite s1, Sprite s2)*__
        - Used to get the draw order for sprites within the Screen class.
        
## class Screen

    + __*Draw(Sprite e, int x, int y, DRAW_ORDER drawOrder)*__
        - Adds a sprite to the draw queue.
            - The draw queue is sorted based on drawOrder on render.
            
    + __*clear()*__
        - Clears all previous pixel data.
        
    + __*Render()*__
        - Sorts draw queue (sprites) by DRAW_ORDER
        - Computes the pixel data for all sprites added to draw queue.
        
    + __*Tick()*__
        - Unused
        
    + __*AddEntity(Entity e)*__
        - Adds an entity to the current level stored in Screen if not already added
        
    + __*RemoveEntity(Entity e)*__
        - Removes an entity from the current level stored in Screen if found

    + __*renderSprite(int xOffset, int yOffset, Sprite sprite)*__
         - Alters stored pixels according to the xOffset and yOffset, as well as the Sprite dimensions
        
**Sprite.java:**

- Class used to store visual information about a renderable target, such as an Entity.


**Spritesheet.java:**

- Class used to store visual information about a readable picture file. Picture file is converted to pixel data.
