VERSION=DEV-SNAPSHOT

run-dev:
	mvn -pl runner-quarkus quarkus:dev

native:
	mvn verify -Pnative

run-native:
	./runner-quarkus/target/naolo-runner-quarkus-${VERSION}-runner
