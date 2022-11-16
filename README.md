# GridEngine v0.7.4

---

This is a 2D engine developed for ease of use. The idea behind the engine itself is to allow 'grid-like' snapping for player position, but not require it.
- There will still be fluid motion available for use.

---

## Documentation

**Runner.java:**

- Main class for the engine.

    + __*start()*__
        - Start the engine
        - Initializes required elements of the engine, such as entity storage
       
    + __*stop()*__
        - Stop the engine
        - Should only be called on engine termination
        
**Screen.java:**

- Class used to handle all rendering elements. Stores an array of pixels to be read/cleared by Runner.java

     + __*renderSprite(int xOffset, int yOffset, Sprite sprite)*__
         - Alters stored pixels according to the xOffset and yOffset, as well as the Sprite dimensions
        
**Sprite.java:**

- Class used to store visual information about a renderable target, such as an Entity.


**Spritesheet.java:**

- Class used to store visual information about a readable picture file. Picture file is converted to pixel data.
