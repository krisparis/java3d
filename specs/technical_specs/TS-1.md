# Technical Specification 1

## Name:
Physics engine

## Objectives:
Simulate realistic ball motion 


## Physical laws:


### velocity, displacement and acceleration

**velocity** <span style="color:blue">`V`</span> is the rate of change of <br/>
**displacement** <span style="color:green">`D`</span> over time, <br/>
and **acceleration** <span style="color:red">`A`</span> is the rate of change
of velocity over time.

- <span style="color:blue">`V`</span> = d<span style="color:green">`D`</span>/dt
- <span style="color:red">`A`</span> = d<span style="color:blue">`V`</span>/dt

*Note:* velocity, displacement and acceleration are used for describing three-dimensional vectors rather than one-dimensional scalars.

### orientation, angular velocity and angular acceleration

Let <span style="color:green">`O`</span>  denote the **orientation** (or angle) of the ball, expressed in radians<br/>
<span style="color:blue">`AV`</span>  denote the **angular velocity** of the ball <br/>
<span style="color:red">`AA`</span>  denote the **angular acceleration** of the ball

- <span style="color:green">`O`</span> = s/r, where **s** is the arc length swept out by the angle <span style="color:green">`O`</span>, and **r** is the radius of the corresponding circle
- <span style="color:blue">`AV`</span> = d<span style="color:green">`O`</span>/dt
- <span style="color:red">`AA`</span> = d<span style="color:blue">`AV`</span>/dt


### Relation between Force and acceleration

- Fe **=** m **x** <span style="color:red">`A`</span>, where **m** is the mass of the ball and **Fe** the external forces acting on the ball

### Free fall

In the case of a free fall, the **Weight** is the only force acting on the ball. <br/>
Let **W** denote the weight force.

- W = m x g, where **g** is the standard gravity, which value is 9.81 m/(s)^2
- Since *Fe = W*, we have : 
 - m **x** <span style="color:red">`A`</span> = m **x** g and
 -  <span style="color:red">`A`</span> = g = 9.81 m/(s)^2



