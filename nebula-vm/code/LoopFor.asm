; T t
.class LoopFor
.field name

.def main: args=0, locals=8

	iconst r1,0
	iconst r2,10
	
	iconst r3,0
	iconst r4,10000
	iconst r5,1
start:
	forprep r3,done
	
	iadd r1,r1,r2
	
	forloop r3,start
done:
	sconst r4, "done"
	print r4
	print r1
	halt
