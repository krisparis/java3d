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
<span style="color:blue">`W`</span>  denote the **angular velocity** of the ball <br/>
<span style="color:red">`AA`</span>  denote the **angular acceleration** of the ball

- <span style="color:green">`O`</span> = s/r, where **s** is the arc length swept out by the angle <span style="color:green">`O`</span>, and **r** is the radius of the corresponding circle
- <span style="color:blue">`W`</span> = d<span style="color:green">`O`</span>/dt
- <span style="color:red">`AA`</span> = d<span style="color:blue">`W`</span>/dt


### Relation between Force and acceleration

- Fe **=** m **.** <span style="color:red">`A`</span>, where **m** is the mass of the ball and **Fe** the external forces acting on the ball

### Free fall

In the case of a free fall, the **Weight** is the only force acting on the ball. <br/>
Let **Wf** denote the weight force.

- Wf = m **.** g, where **g** is the standard gravity, which value is 9.81 m/(s)^2
- Since *Fe = Wf*, we have : 
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
- <span style="color:blue">`W_n`</span> = <span style="color:red">`AA_n`</span> **.** <span style="color:purple">`TS`</span> **+** <span style="color:blue">`W_n-1`</span>

Finally the next state of the ball can be calculated:

- <span style="color:blue">`V_n+1`</span> **=** <span style="color:blue">`V_n`</span> **+** <span style="color:red">`A_n`</span> **.** <span style="color:purple">`TS`</span>
- <span style="color:green">`D_n+1`</span> **=** <span style="color:green">`D_n`</span> **+** <span style="color:blue">`V_n+1`</span> **.** <span style="color:purple">`TS`</span>

and

- <span style="color:blue">`W_n+1`</span> **=** <span style="color:blue">`W_n`</span> **+** <span style="color:red">`AA_n`</span> **.** <span style="color:purple">`TS`</span>
- <span style="color:green">`O_n+1`</span> **=** <span style="color:green">`O_n`</span> **+** <span style="color:blue">`W_n+1`</span> **.** <span style="color:purple">`TS`</span>

#### Case of collision

Handling a collision includes to steps:

- Collision detection
- Collision response

##### Collision detection

Detecting a collision includes to steps:

1. Broadphase
2. Narrowphase

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

During the Narrowphase, several algorithms can be used for detecting collision between objects.

One of these algorithms enable developers to detect collision between a sphere (the ball) and a plane (the ground).


###### Narrowphase : Sphere - Plane Collision

![Ball penetration](https://github.com/krisparis/java3d/blob/master/specs/technical_specs/img/TS-1_img/ball_penetration.png?raw=true)

**Collision detection equation**

Below are the elements of the collision detection equation

<b><span style="color:black">`n`</span></b>: the normal vector of the plane <br/>
<b><span style="color:black">`OS`</span></b>: the vector from the origin to the position of the test point <br/>
<b><span style="color:black">`DO`</span></b>: the vector from the plane to the origin<br/>
*r:* the radius of the plane

Consequently, a sphere at position *S* of radius *r*, intersects a plane with normal *N* at distance |DO| from the origin if

<b><span style="color:black">`N`</span></b> **.** <b><span style="color:black">`OS`</span></b> **+** <b><span style="color:black">`N`</span></b> **.** <b><span style="color:black">`DO`</span></b> **<** r


By definition of the dot product:

- <b><span style="color:black">`n`</span></b> **.** <b><span style="color:black">`OS`</span></b> **+** <b><span style="color:black">`n`</span></b> **.** <b><span style="color:black">`DO`</span></b> **=** 1 **x** |OH| **-** 1 **X** |DO| **=** |OH| **-** |DO|, where H is the projection of point S on the line (OD).

In the case of the above diagram, |OH| **-** |DO| corresponds to the length of the segment HD.


**Penetration**

In case the ball has already penetrated the plane,
the **penetration** *p* is simply the difference between the radius and the distance between the sphere centre and the plane.

- p = r **-** **(** <b><span style="color:black">`n`</span></b> **.** <b><span style="color:black">`OS`</span></b> **+** <b><span style="color:black">`n`</span></b> **.** <b><span style="color:black">`DO`</span></b> **)** 

The **contact point** P is calculated by
taking the sphere position, and adding a vector along the direction of the normal equal to the distance
between the sphere centre and the plane

- P = S **-** <b><span style="color:black">`n`</span></b> **.** **(** r **-** p **)**


##### Collision response

The collision response includes several steps:

1. In case the ball collides with another object, the ball is moved back to a previous position that does not intersect with that object.
2. Then the collision response is calculated
3. The ball's velocity and position are updated


###### Case of Sphere - Plane Collision


**Moving the ball back to a previous positon**

In case the ball has already penetrated the plane, the new position of the center of the ball becomes:  <br/>
S2 = S **-** <b><span style="color:black">`n`</span></b> **.**  p


**Calculate collision response**

*Linear collision response*

The **impulse** method is used for determining post-collision velocities of two colliding objects, A and B.

This method includes the following steps:

1. Determine the relative pre-collision velocity of the two colliding objects 
2. Calculate post-collision linear velocities using momentum
3. Calculate post-collision angular velocities using momentum

*Step 1: Determine the relative pre-collision velocity*

![Relative pre-collision velocity](https://github.com/krisparis/java3d/blob/master/specs/technical_specs/img/TS-1_img/collision_velocity_vectors.png?raw=true)

- <span style="color:blue">`Va-`</span> **:** Pre-collision velocity of sphere A
- <span style="color:blue">`Vb-`</span> **:** Pre-collision velocity of sphere B
- <span style="color:blue">`Vab-`</span> **=** <span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span> **:** Relative pre-collision relative velocity of sphere A and B
- <b><span style="color:black">`n`</span></b> **:** The collision normal (from A to B)
- <span style="color:blue">`Vn-`</span> **=** <span style="color:blue">`Vab-`</span> **.** <span style="color:blue">`n`</span>  **:** Projection of the relative velocity onto normal


*Step 2: Calculate post-collision linear velocities using momentum*


Let's first define the term momentum. <br/>
**Momentum** *p* is defined by the following equation:

- p **=** m **.** <span style="color:blue">`dv`</span>
- F **=**   dp **/** <span style="color:purple">`dt`</span>, where **F** is the force applied on this object
and <span style="color:purple">`dt`</span> is a time period 

During the time two objects A and B interact,
object A exerts the impulse force **J** on object B along the collision normal. By reaction object B exerts an **equal and opposite** force **-J** on object A (from Newton's third law).

- -J  **.** <span style="color:blue">`n`</span> = dp\_a **/** <span style="color:purple">`dt`</span>
- J  **.** <span style="color:blue">`n`</span> = dp\_b **/** <span style="color:purple">`dt`</span>

The two colliding objects represent an *isolated system*, i.e which is free from the influence of a net external force that alters the momentum of the system. <br/>
Therefore by the **law of conservation of the momentum**, the total amount of momentum of the collection of objects in the system  **is constant** before the collision as after the collision.

So:
 
- -J  **.** <span style="color:blue">`n`</span> = dp\_a **/** <span style="color:purple">`dt`</span> = - Ma **.** **(** <span style="color:blue">`Va+`</span> - <span style="color:blue">`Va-`</span> **)** **/** <span style="color:purple">`dt`</span>
- J  **.** <span style="color:blue">`n`</span> = dp\_b **/** <span style="color:purple">`dt`</span> = Mb **.** **(** <span style="color:blue">`Vb+`</span> - <span style="color:blue">`Vb-`</span> **)** **/** <span style="color:purple">`dt`</span>
- **So** <span style="color:blue">`Va+`</span> **=** J  **.** <span style="color:blue">`n`</span> **/** Ma + <span style="color:blue">`Va-`</span>
- **So** <span style="color:blue">`Vb+`</span> **=** J  **.** <span style="color:blue">`n`</span> **/** Mb - <span style="color:blue">`Vb-`</span>

The velocity along the normal after collision is dependent on the **coefficient of elasticity** *epsilon*.

- <span style="color:blue">`Vn+`</span> **.** <span style="color:black">`n`</span> = *epsilon* **.** <span style="color:blue">`Vn-`</span> **.** <span style="color:black">`n`</span>
- **(**<span style="color:blue">`Va+`</span> **-** <span style="color:blue">`Vb+`</span>**)** **.** <span style="color:black">`n`</span> = *epsilon* **.** **(**<span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span>**)** **.** <span style="color:black">`n`</span>


 A coefficient of 1 means the collision will be purely **elastic**, so all the velocity is transferred, whereas
a coefficient of zero is purely **non-elastic,** so no velocity is transferred. A purely non-elastic collision will result in the two bodies staying together (i.e. no bounce); a purely elastic collision is a perfect
bounce so no damping or slowing down occurs.

In the equation:

- **(**<span style="color:blue">`Va+`</span> **-** <span style="color:blue">`Vb+`</span>**)** **.** <span style="color:black">`n`</span> = *epsilon* **.** **(**<span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span>**)** **.** <span style="color:black">`n`</span>

Let's sustitute the variables <span style="color:blue">`Va+`</span> and <span style="color:blue">`Vb+`</span> using the following equations:

- <span style="color:blue">`Va+`</span> **=** J  **.** <span style="color:blue">`n`</span> **/** Ma + <span style="color:blue">`Va-`</span>
- <span style="color:blue">`Vb+`</span> **=** J  **.** <span style="color:blue">`n`</span> **/** Mb - <span style="color:blue">`Vb-`</span>

This results in:

- **(** J  **.** <span style="color:blue">`n`</span> **.** <span style="color:blue">`n`</span> **/** Ma + <span style="color:blue">`Va-`</span> **-** J  **.** <span style="color:blue">`n`</span> **.** <span style="color:blue">`n`</span> **/** Mb - <span style="color:blue">`Vb-`</span> **)** **.** <span style="color:black">`n`</span> = *epsilon* **.** **(**<span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span>**)** **.** <span style="color:black">`n`</span>
- J  **.** <span style="color:blue">`n`</span> **.** <span style="color:blue">`n`</span> **.** **(** 1 **/** Ma **-** 1 **/** Mb **)** **+** **(** <span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span> **)** **.** <span style="color:blue">`n`</span> **=** *epsilon* **.** **(**<span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span>**)** **.** <span style="color:black">`n`</span>
- J  **.** <span style="color:blue">`n`</span> **.** <span style="color:blue">`n`</span> **.** **(** 1 **/** Ma **-** 1 **/** Mb **)** **=** **(**-1 **+** *epsilon* **)** **(** <span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span> **)** **.** <span style="color:black">`n`</span>
- J  **.** <span style="color:blue">`n`</span> **.** <span style="color:blue">`n`</span> **=** **(**-1 **+** *epsilon* **)** **(** <span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span> **)** **.** <span style="color:black">`n`</span> **/** **(** 1 **/** Ma **-** 1 **/** Mb **)**
- J  **=** **(**-1 **+** *epsilon* **)** **(** <span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span> **)** **.** <span style="color:black">`n`</span> **/** **[** <span style="color:blue">`n`</span> **.** <span style="color:blue">`n`</span> **.** **(** 1 **/** Ma **-** 1 **/** Mb **)** **]**

*Step 3: Calculate post-collision angular velocities using momentum*

![Relative pre-collision velocity](https://github.com/krisparis/java3d/blob/master/specs/technical_specs/img/TS-1_img/collision_angular_velocity_vectors.png?raw=true)

If angular motion is taken into account when calculating collision response, then the velocity 
of the actual contact points on each object should be calculated.

For two objects A and B:

- <span style="color:blue">`Wa-`</span> : the angular velocity vector of the contact point of object A 
- <span style="color:blue">`Ra`</span> : the vector from the centre of object A to its contact point
- <span style="color:blue">`Wb-`</span> : the angular velocity vector of the contact point of object B
- <span style="color:blue">`Rb`</span> : the vector from the centre of object B to its contact point

The pre-collision velocities of the two objects at their contact points are given by the following equations:

- <span style="color:blue">`VCa-`</span> **=** <span style="color:blue">`Va-`</span> **+** <span style="color:blue">`Wa-`</span> **x** <span style="color:blue">`Ra`</span>
- <span style="color:blue">`VCb-`</span> **=** <span style="color:blue">`Vb-`</span> **+** <span style="color:blue">`Wb-`</span> **x**  <span style="color:blue">`Rb`</span>


Let's first define the term angular momentum. <br/>
**Angular Momentum** *L* is defined by the following equation:

- L **=** M\_I **.** <span style="color:blue">`W`</span>
- *Torque* **=** d_torque x F **=**   M\_I  **.** <span style="color:red">`AA`</span> **=**  M\_I **.** d<span style="color:blue">`W`</span> **/** <span style="color:purple">`dt`</span>,
where <span style="color:purple">`dt`</span> is a time period
- **So** d_torque x F **=**  M\_I **.** d<span style="color:blue">`W`</span> **/** <span style="color:purple">`dt`</span>

During the time two objects A and B interact,
object A exerts the impulse force **J** on object B along the collision normal. By reaction object B exerts an **equal and opposite** force **-J** on object A (from Newton's third law).

- d\_torque **x** -J  **.** <span style="color:blue">`n`</span> **=** <span style="color:blue">`Ra`</span> **x** -J  **.** <span style="color:blue">`n`</span> **=** M\_Ia **.** d<span style="color:blue">`W`</span> **/** <span style="color:purple">`dt`</span>
- d\_torque **x** J  **.** <span style="color:blue">`n`</span> **=** <span style="color:blue">`Rb`</span> **x** J  **.** <span style="color:blue">`n`</span> **=** M\_Ib **.** d<span style="color:blue">`W`</span> **/** <span style="color:purple">`dt`</span>

The two colliding objects represent an *isolated system*, i.e which is free from the influence of a net external force that alters the momentum of the system. <br/>
Therefore by the **law of conservation of the momentum**, the total amount of momentum of the collection of objects in the system  **is constant** before the collision as after the collision.

Therefore:

- <span style="color:blue">`Ra`</span> **x** -J  **.** <span style="color:blue">`n`</span> **=** M\_Ia **.** d<span style="color:blue">`W`</span> **/** <span style="color:purple">`dt`</span> **=** M\_Ia **.** **(** <span style="color:blue">`Wa+`</span> **-** <span style="color:blue">`Wa-`</span> **)** **/** <span style="color:purple">`dt`</span>
- <span style="color:blue">`Rb`</span> **x** J  **.** <span style="color:blue">`n`</span> **=** M\_Ib **.** d<span style="color:blue">`W`</span> **/** <span style="color:purple">`dt`</span> **=** M\_Ib **.** **(** <span style="color:blue">`Wb+`</span> **-** <span style="color:blue">`Wb-`</span> **)** **/** <span style="color:purple">`dt`</span>
- **So** <span style="color:blue">`Wa+`</span> **=**  <span style="color:blue">`Wa-`</span> **-**  M\_Ia^(-1) **.** <span style="color:blue">`Ra`</span> **x** J **.** <span style="color:blue">`n`</span>
- **So** <span style="color:blue">`Wb+`</span> **=** <span style="color:blue">`Wb-`</span> **+** M\_Ib^(-1) **.** <span style="color:blue">`Rb`</span> **x** J  **.** <span style="color:blue">`n`</span> 



In the equation:

- **(**<span style="color:blue">`Va+`</span> **-** <span style="color:blue">`Vb+`</span>**)** **.** <span style="color:black">`n`</span> = *epsilon* **.** **(**<span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span>**)** **.** <span style="color:black">`n`</span>


Let's replace the variables <span style="color:blue">`Va+`</span> and <span style="color:blue">`Vb+`</span> with the variables <span style="color:blue">`VCa+`</span> and <span style="color:blue">`VCb+`</span> respectively:

- **(**<span style="color:blue">`VCa+`</span> **-** <span style="color:blue">`VCb+`</span>**)** **.** <span style="color:black">`n`</span> = *epsilon* **.** **(**<span style="color:blue">`VCa-`</span> **-** <span style="color:blue">`VCb-`</span>**)** **.** <span style="color:black">`n`</span>

Let's sustitute the variables <span style="color:blue">`VCa+`</span>, <span style="color:blue">`VCa+`</span>, <span style="color:blue">`VCb+`</span> and <span style="color:blue">`VCb-`</span> using the following equations:

- <span style="color:blue">`VCa+`</span> **=** <span style="color:blue">`Va+`</span> **+** <span style="color:blue">`Wa+`</span> **x** <span style="color:blue">`Ra`</span>
- <span style="color:blue">`VCb+`</span> **=** <span style="color:blue">`Vb+`</span> **+** <span style="color:blue">`Wb+`</span> **x**  <span style="color:blue">`Rb`</span>
- <span style="color:blue">`VCa-`</span> **=** <span style="color:blue">`Va-`</span> **+** <span style="color:blue">`Wa-`</span> **x** <span style="color:blue">`Ra`</span>
- <span style="color:blue">`VCb-`</span> **=** <span style="color:blue">`Vb-`</span> **+** <span style="color:blue">`Wb-`</span> **x**  <span style="color:blue">`Rb`</span>

This results in:


- **(** <span style="color:blue">`Va+`</span> **+** <span style="color:blue">`Wa+`</span> **x** <span style="color:blue">`Ra`</span> **-** <span style="color:blue">`Vb+`</span> **-** <span style="color:blue">`Wb+`</span> **x** <span style="color:blue">`Rb`</span> **)** **.** <span style="color:black">`n`</span> = *epsilon* **.** **(** <span style="color:blue">`Va-`</span> **+** <span style="color:blue">`Wa-`</span> **x** <span style="color:blue">`Ra`</span> **-** <span style="color:blue">`Vb-`</span> **-** <span style="color:blue">`Wb-`</span> **x**  <span style="color:blue">`Rb`</span> **)** **.** <span style="color:black">`n`</span>
- **(** J **.** <span style="color:blue">`n`</span> **/** Ma **+** <span style="color:blue">`Va-`</span> **+** <span style="color:blue">`Wa+`</span> **x** <span style="color:blue">`Ra`</span> **-** J  **.** <span style="color:blue">`n`</span> **/** Mb - <span style="color:blue">`Vb-`</span> **-** <span style="color:blue">`Wb+`</span> **x**  <span style="color:blue">`Rb`</span> **)** **.** <span style="color:black">`n`</span> = *epsilon* **.** **(** <span style="color:blue">`Va-`</span> **+** <span style="color:blue">`Wa-`</span> **x** <span style="color:blue">`Ra`</span> **-** <span style="color:blue">`Vb-`</span> **-** <span style="color:blue">`Wb-`</span> **x**  <span style="color:blue">`Rb`</span> **)** **.** <span style="color:black">`n`</span> **,** *(substitution of <span style="color:blue">`Va+`</span> and <span style="color:blue">`Vb+`</span>)*
- **[**  J  **.** <span style="color:blue">`n`</span> **/** Ma **+** <span style="color:blue">`Va-`</span> **+** **(** <span style="color:blue">`Wa-`</span> **-**  M\_Ia^(-1) **.** <span style="color:blue">`Ra`</span> **x** J **.** <span style="color:blue">`n`</span> **)** **x** <span style="color:blue">`Ra`</span> **-** J  **.** <span style="color:blue">`n`</span> **/** Mb **-** <span style="color:blue">`Vb-`</span> **-** **(** <span style="color:blue">`Wb-`</span> **+** M\_Ib^(-1) **.** <span style="color:blue">`Rb`</span> **x** J  **.** <span style="color:blue">`n`</span>  **)** **x**  <span style="color:blue">`Rb`</span>  **]** **.** <span style="color:black">`n`</span> **=** *epsilon* **.** **(** <span style="color:blue">`Va-`</span> **+** <span style="color:blue">`Wa-`</span> **x** <span style="color:blue">`Ra`</span> **-** <span style="color:blue">`Vb-`</span> **-** <span style="color:blue">`Wb-`</span> **x**  <span style="color:blue">`Rb`</span> **)** **.** <span style="color:black">`n`</span>
- **[** J  **.** <span style="color:blue">`n`</span> **.** **(** 1 **/** Ma **+** 1 **/** Mb **)** **+** <span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span> **+** <span style="color:blue">`Wa-`</span> **x** <span style="color:blue">`Ra`</span> **+**  M\_Ia^(-1) **.** J **.** <span style="color:blue">`n`</span> **.** <span style="color:blue">`Ra`</span>  **x** <span style="color:blue">`Ra`</span> **-** <span style="color:blue">`Wb-`</span> **x**  <span style="color:blue">`Rb`</span> **-** M\_Ib^(-1) **.** J  **.** <span style="color:blue">`n`</span> **.** <span style="color:blue">`Rb`</span> **x** **x**  <span style="color:blue">`Rb`</span> **]** **.** <span style="color:black">`n`</span> **=** *epsilon* **.** **(** <span style="color:blue">`Va-`</span> **+** <span style="color:blue">`Wa-`</span> **x** <span style="color:blue">`Ra`</span> **-** <span style="color:blue">`Vb-`</span> **-** <span style="color:blue">`Wb-`</span> **x**  <span style="color:blue">`Rb`</span> **)** **.** <span style="color:black">`n`</span>
- J  **.** <span style="color:blue">`n`</span> **.** <span style="color:blue">`n`</span> **.** **[** **(** 1 **/** Ma **+** 1 **/** Mb **)** **+**  **(** M\_Ia^(-1) **.** <span style="color:blue">`Ra`</span> **)** **x** <span style="color:blue">`Ra`</span>  **+**  **(** M\_Ib^(-1) **.** <span style="color:blue">`Rb`</span> **)** **x** <span style="color:blue">`Rb`</span>  **]** **+** **(** <span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span>  **+** <span style="color:blue">`Wa-`</span> **x** <span style="color:blue">`Ra`</span> **+** <span style="color:blue">`Wb-`</span> **x**  <span style="color:blue">`Rb`</span> **)** **.** <span style="color:black">`n`</span> **=** *epsilon* **.** **(** <span style="color:blue">`Va-`</span> **+** <span style="color:blue">`Wa-`</span> **x** <span style="color:blue">`Ra`</span> **-** <span style="color:blue">`Vb-`</span> **-** <span style="color:blue">`Wb-`</span> **x**  <span style="color:blue">`Rb`</span> **)** **.** <span style="color:black">`n`</span>
- J  **.** <span style="color:blue">`n`</span> **.** <span style="color:blue">`n`</span> **.** **[** **(** 1 **/** Ma **+** 1 **/** Mb **)** **+**  **(** M\_Ia^(-1) **.** <span style="color:blue">`Ra`</span> **)** **x** <span style="color:blue">`Ra`</span>  **+**  **(** M\_Ib^(-1) **.** <span style="color:blue">`Rb`</span> **)** **x** <span style="color:blue">`Rb`</span>  **]** **=** **(** -1 + *epsilon* **)** **.** **(** <span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span> **)** **.** <span style="color:black">`n`</span>
- J   **=** **(** -1 + *epsilon* **)** **.** **(** <span style="color:blue">`Va-`</span> **-** <span style="color:blue">`Vb-`</span> **)** **.** <span style="color:black">`n`</span> **/** <span style="color:blue">`n`</span> **.** <span style="color:blue">`n`</span> **.** **[** **(** 1 **/** Ma **+** 1 **/** Mb **)** **+**  **(** M\_Ia^(-1) **.** <span style="color:blue">`Ra`</span> **)** **x** <span style="color:blue">`Ra`</span>  **+**  **(** M\_Ib^(-1) **.** <span style="color:blue">`Rb`</span> **)** **x** <span style="color:blue">`Rb`</span>  **]**
