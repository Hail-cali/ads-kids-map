# ads-kids-map

discover kid's friendly places activities and events

## Local Docker tunnel

The application can be started with a single local entry point at `http://localhost:8080`.
MongoDB, Redis, and Kafka-compatible Redpanda run on the internal Docker network only;
only the Spring Boot API port is published to the host.

```bash
docker compose up --build
```

Useful endpoints:

- Health: `http://localhost:8080/actuator/health`
- Places: `http://localhost:8080/api/v1/internal/places`
- Tracker debug: `http://localhost:8080/ads/trackers/v1?data=hello`

To expose the app through a local tunneling tool, point the tunnel at the single published
port:

```bash
lt --port 8080
```

Or use any equivalent tunnel provider with `localhost:8080` as the target.
