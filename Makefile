VERSION=DEV-SNAPSHOT


install:
	mvn install

run-dev:
	mvn -pl runner quarkus:dev \
		-Dugiggle.trello-key=${TRELLO_KEY} \
		-Dugiggle.trello-access-token=${TRELLO_SECRET}

native:
	mvn verify -Pnative

run-native:
	./runner/target/ugiggle-runner-${VERSION}-runner \
		-Dugiggle.trello-key=${TRELLO_KEY} \
		-Dugiggle.trello-access-token=${TRELLO_SECRET}

