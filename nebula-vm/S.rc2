.class T4

.def main: args=0, locals=10
	iconst r1, 10
	call r3, @T4.f(), r1
	print r3
	halt
	
.def f: args=1, locals=10 ; int f(int x)
	iconst 	r2,2  		; get int 2 into 1st non-arg register
	struct  r3,@T
	imul 	r4,r1,r2  	; mult arg by 2
	iconst 	r5,30     	; get int 2 into a reg
	call 	r6,@T4.g(),r4 	; leave result in r0; args=[r3,r4]
	ret     r6,1      	; return value in r0
	
.def g: args=2, locals=1 ; int g(int x, int y)
  	sconst r3,"Hello world"   	; return x+y
  	;print r1
	ret  r3,1      		; return value in r2
