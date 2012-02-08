.class T4
.field name

.def main: args=0, locals=2
	iconst r1, 10
	call r2, @T4.f(), r1
	print r2
	halt
	
.def f: args=1, locals=4 ; int f(int x)
	iconst 	r2,2  		; get int 2 into 1st non-arg register
	imul 	r3,r1,r2  	; mult arg by 2
	iconst 	r4,30     	; get int 2 into a reg
	call 	r5,@T4.g(),r3 	; leave result in r0; args=[r3,r4]
	ret     r5,1      	; return value in r0
	
.def g: args=2, locals=1 ; int g(int x, int y)
  	iadd r3,r1, r2   	; return x+y
	ret  r3,1      		; return value in r0
