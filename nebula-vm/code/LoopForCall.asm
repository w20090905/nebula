; T t
.class LoopForCall
.field name

.def main: args=0, locals=8
	iconst r1,0
	iconst r2,10
	
	iconst r3,0
	iconst r4, 10000
	iconst r5,1
start:
	forprep r3,done
	
	call r6,@LoopForCall.add(),r3
	
	forloop r3,start
done:
	sconst r4, "done"
	print r6
	halt

.def add: args=2, locals=1
	iadd r3,r1,r2
	ret r3,1