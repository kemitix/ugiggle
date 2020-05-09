VERSION=DEV-SNAPSHOT

PARAMS=		-Dugiggle.trello-key=${TRELLO_KEY} \
       		-Dugiggle.trello-access-token=${TRELLO_SECRET} \
       		-Dugiggle.amazon-ses-region=${AMAZON_SES_REGION} \
       		-Dugiggle.recipient=${UGIGGLE_RECIPIENT} \
       		-Dugiggle.sender=${UGIGGLE_SENDER}

install:
	mvn install

run-dev:
	mvn -pl runner quarkus:dev ${PARAMS}

native:
	mvn verify -Pnative

run-native:
	./runner/target/ugiggle-runner-${VERSION}-runner ${PARAMS}
