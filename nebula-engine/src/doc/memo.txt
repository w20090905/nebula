ab -c 1 -n 10 -T 'application/x-www-form-urlencoded' -p post.txt http://localhost/d/Person

ab -c 30 -n 1000 -T http://192.168.20.88/d/Person/wangshi11


//don't use code
//copy sqlite_jni.dll D:\EnvBase\jdk1.7.0\jre\bin\
//mvn install:install-file -DgroupId=de.ch-werner -DartifactId=javasqlite -Dversion=1.0 -Dfile=sqlite.jar -Dpackaging=jar -DgeneratePom=true
