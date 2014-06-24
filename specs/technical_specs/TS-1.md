# Technical Specification 1

## Name
Physics engine

## Objectives
Simulate realistic ball motion 


## Physical laws


### Velocity, displacement and acceleration

**velocity** <span style="color:blue">`V`</span> is the rate of change of <br/>
**displacement** <span style="color:green">`D`</span> over time, <br/>
and **acceleration** <span style="color:red">`A`</span> is the rate of change
of velocity over time.

- <span style="color:blue">`V`</span> = d<span style="color:green">`D`</span>/dt
- <span style="color:red">`A`</span> = d<span style="color:blue">`V`</span>/dt

*Note:* velocity, displacement and acceleration are used for describing three-dimensional vectors rather than one-dimensional scalars.

### Orientation, angular velocity and angular acceleration

Let <span style="color:green">`O`</span>  denote the **orientation** (or angle) of the ball, expressed in radians<br/>
<span style="color:blue">`AV`</span>  denote the **angular velocity** of the ball <br/>
<span style="color:red">`AA`</span>  denote the **angular acceleration** of the ball

- <span style="color:green">`O`</span> = s/r, where **s** is the arc length swept out by the angle <span style="color:green">`O`</span>, and **r** is the radius of the corresponding circle
- <span style="color:blue">`AV`</span> = d<span style="color:green">`O`</span>/dt
- <span style="color:red">`AA`</span> = d<span style="color:blue">`AV`</span>/dt


### Relation between Force and acceleration

- Fe **=** m **.** <span style="color:red">`A`</span>, where **m** is the mass of the ball and **Fe** the external forces acting on the ball

### Free fall

In the case of a free fall, the **Weight** is the only force acting on the ball. <br/>
Let **W** denote the weight force.

- W = m **.** g, where **g** is the standard gravity, which value is 9.81 m/(s)^2
- Since *Fe = W*, we have : 
 - m **.** <span style="color:red">`A`</span> = m **x** g and
 -  <span style="color:red">`A`</span> = g = 9.81 m/(s)^2

### Relation between Torque and angular acceleration

- <span style="color:red">`AA_n`</span> **=** *Torque* **.** M\_I ^(-1), where **M\_I** is the Inertia Matrix.
- *Torque* is the tendency of a force to rotate an object about an axis, fulcrum, or pivot

#### Calculation of Torque



The value of *Torque* is given by the following equation:

*Torque* = *d\_torque* **x** Fe  **(cross-product of vectors)**, where *d\_torque* is  the displacement vector between the center of gravity of the ball and the surface of the ball



#### Inertia Matrix

In case of a ball simulated as a solid sphere, the values of the matrix are as shown below:

In this matrix, the value I is such as **I** = [2 x **m** x (**r**)^2] / 5, where **r** is the radius of the sphere and **m** is its mass.
<table>
<tr>
<td> <i>I</i> </td>
<td> 0 </td>
<td> 0 </td>
</tr>
<tr>
<td> 0 </td>
<td> <i>I</i> </td>
<td> 0 </td>
</tr>
<tr>
<td> 0 </td>
<td> 0 </td>
<td> <i>I</i></td>
</tr>
</table>


## Ball motion simulation

Computers deal with discrete numbers and states; if the simulation
runs sufficiently quickly then we see these states as continuous movement. For each frame of the simulation, we need to calculate the state of each object (i.e. the velocity and position), based on the
state of the object in the previous frame.

The amount of simulated time that has elapsed between each
step of the simulation is known as the **time step**, denoted as <span style="color:purple">`TS`</span>.

### Calculating the next state of the ball

#### Failing ball

The first step is to calculate the ball's acceleration and angular acceleration for the current frame.

- <span style="color:red">`A_n`</span> **=** Fe **/** m
- <span style="color:red">`AA_n`</span> **=** M\_I ^(-1) **.** *Torque*

Then the current velocity and angular velocity of the ball can be calculated

- <span style="color:blue">`V_n`</span> = <span style="color:red">`A_n`</span> **.** <span style="color:purple">`TS`</span> **+** <span style="color:blue">`V_n-1`</span>
- <span style="color:blue">`AV_n`</span> = <span style="color:red">`AA_n`</span> **.** <span style="color:purple">`TS`</span> **+** <span style="color:blue">`AV_n-1`</span>

Finally the next state of the ball can be calculated:

- <span style="color:blue">`V_n+1`</span> **=** <span style="color:blue">`V_n`</span> **+** <span style="color:red">`A_n`</span> **.** <span style="color:purple">`TS`</span>
- <span style="color:green">`D_n+1`</span> **=** <span style="color:green">`D_n`</span> **+** <span style="color:blue">`V_n+1`</span> **.** <span style="color:purple">`TS`</span>

and

- <span style="color:blue">`AV_n+1`</span> **=** <span style="color:blue">`AV_n`</span> **+** <span style="color:red">`AA_n`</span> **.** <span style="color:purple">`TS`</span>
- <span style="color:green">`O_n+1`</span> **=** <span style="color:green">`O_n`</span> **+** <span style="color:blue">`AV_n+1`</span> **.** <span style="color:purple">`TS`</span>

#### Case of collision

Handling a collision includes to steps:

- Collision detection
- Collision response

##### Collision detection

Detecting a collision includes to steps:

- Broadphase
- Narrowphase

###### Broadphase

- Need to test ball collision with other objects
- Try to avoid useless collision tests

**Space partitioning:** 

- Divide the game world into sections
- At each simulation frame:
 - Identify which section each object belongs to
 - Check any collision between any objects of this section

*Note:* A mobile object can pass through several sections over time.

###### Narrowphase

During the Narrowphase, several algorithms can be used for detecting collision between ojects.

One of these algorithms enable developers to detect collision between a sphere (the ball) and a surface (the ground).


####### Sphere - Plane Collision

**Collision detection equation**

Below are the elements of the collision detection equation

*N:* the normal of the plane <br/>
*S:* the position of the test point <br/>
*d:* the distance of the plane from the origin <br/>
*r:* the radius of the plane

Consequently, a sphere at position *S* of radius *r*, intersects a plane with normal *N* at distance *d* from the origin if

N**.**S **+** d **<** r

**Penetration**

![](https://github.com/krisparis/java3d/blob/master/specs/technical_specs/img/TS-1_img/ball_penetration.png)

In case the ball has already penetrated the plane,
the **penetration** *p* is simply the difference between the radius and the distance between the sphere centre and the plane.

- p = r **-** **(** N **.** S **+** d**)** 

The **contact point** P is calculated by
taking the sphere position, and adding a vector along the direction of the normal equal to the distance
between the sphere centre and the plane

- P = S **-** N **.** **(** r **-** p **)**

##### Collision response

###### Sphere - Plane Collision

**Penetration**

In case the ball has already penetrated another object, the ball should be moved back
to a previous position where the surface of the ball is in contact with the other object.

