VERSION=1.2.0

install:
	mvn install

run-dev:
	mvn -pl runner quarkus:dev

native:
	mvn verify -Pnative

run-native:
	./runner/target/ugiggle-runner-${VERSION}-runner
