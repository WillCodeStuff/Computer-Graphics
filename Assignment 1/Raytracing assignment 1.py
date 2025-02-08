#!/usr/bin/env python
# coding: utf-8

# In[1]:


# imports
import numpy as np
from PIL import Image


# In[2]:


# variables
width = 500
height = 500
viewportSize = 1
projectionPlane = 1

#Scene objects

camera = np.array([0,0,0])

spheres = [{"center": np.array([0,-1,3]), "radius": 1, "color": (255,0,0)},
          {"center": np.array([-2,0,4]), "radius": 1, "color": (0,255,0)},
          {"center": np.array([2,0,4]), "radius": 1, "color": (0,0,255)}
          ]


# In[3]:


def canvasViewport(x,y):
    return np.array([
        x * viewportSize / width,
        y * viewportSize / height,
        projectionPlane
    ])


# In[4]:


def intersectRays(origin, direction, sphere):
    
    center = sphere["center"]
    r = sphere["radius"]
    oc = origin - center
    
    a = np.dot(direction,direction)
    b = 2 * np.dot(oc,direction)
    c = np.dot(oc,oc) - r**2
    
    disc = b**2 - 4*(a*c)
    if (disc < 0):
        return float("inf")
    else:
        return (-b -np.sqrt(disc)) / (2*a)
    
    


# In[5]:


image = Image.new("RGB", (width, height), "white")
pixels = image.load()

#create canvas
for x in range(width):
    for y in range(height):
        viewX = (x - width/2)
        viewY = -(y - height/2)
        direction = canvasViewport(viewX,viewY)
        direction = direction /np.linalg.norm(direction)
        closest = float("inf")
        pixelColor = (255,255,255) #background color if no intersection
        
        #check for ray intersections with spheres
        for item in spheres:
            intersect = intersectRays(camera, direction, item)
            if intersect < closest:
                closest = intersect
                pixelColor = item["color"]
        pixels[x,y] = pixelColor
        


# In[6]:


image.show()


# In[ ]:




