## Start-up
- Launch the docker daemon
- Navigate to the core folder `cd core`
- Run `./gradlew test`

> **Please note**:
> - The application would automatically pull the Postgres-Alpine docker image and run a docker-compose deployment with a DB container
> - The tests would add ~10k rows to the database
> - The process might take up to 3-5 minutes

## Considerations
- While the application works fine with large amounts of data, the DB row-locking mechanism lacks reliability when working under race conditions. The data consistency was achieved but some user requests might fail. This wasn't fixed yet due to the lack of time
- The present Kafka logic placeholder is bound to the Map(Int, Int) return type, which is the result of REST's synchronous communication nature and what limits the ways the message broker can be used.
- Kafka was chosen as a placeholder for a messaging logic thanks to its scalability and ability to store events for reasonable amounts of time, which is relevant for updating millions of money records in the DB.
- Batch and chunks updates were not used as balance updates are too important to be updated in a single transaction

## Possible improvement
As the update process is not that complex (at least for now), it could be implemented using the Saga Choreography pattern.
- Client submits a Map for update;
- A gateway or some service generates a saga update event for each entry. The events should be persisted for a reasonable amount of time;
- The gateway responds to the client with some initial processing state;
- The events are consumed and processed by horizontally-scalable services;
- The services update the saga and produce a result back;
- Then it's either a client who polls the gateway to get the response or the gateway who notifies the client about the response;

In terms of the existing code and placeholders, an imitation of a choreography may be implemented. To do so we have to:
- Implement `KafkaBalanceProcessor` 's return value (Map(Int, Int)) would be a kind of "update submit approval"
- The processor should produce an event for each balance update
- A consumer (likely the same app) would process the update and send a response
- The final response would be consumed and a "processed IDs" list will be extended
- To track the update process the client might need an endpoint to either load the balances, or check the current "processed IDs" list
