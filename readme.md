# JetLink - URL Shortener API

JetLink is a lightweight, high-performance RESTful API designed to shorten long URLs, retrieve original destinations, and manage link lifecycles efficiently.

## Live Base URL
The API is deployed and accessible at:
`https://jettlink-jetlink.hf.space`

---

## API Reference

### 1. Create a New Short Link
* **Endpoint:** `POST /api/v1/urls`
* **Description:** Generates a shortened URL wrapper for a given target destination.
* **Status Code on success:** `201 Created`

#### Request Body (`application/json`)
```json
{
  "originalUrl": "https://example.com"
}
```

#### Response Body (`text/plain`)
```text
jettlink-jetlink.hf.space/1
```

### 2. Get Original Destination

* **Endpoint:** `GET /<shortCode>`
* **Description:** Resolves the unique short code and returns the original long URL destination.
* **Status Code:** `200 OK` / `302 Found`

**Request URL Example**
```
GET https://jettlink-jetlink.hf.space/1
```

### 3. Delete a Short Link

* **Endpoint:** `DELETE /api/v1/urls/<shortCode>`
* **Description:** Removes a shortened link from the database entirely, making the short code inactive.
* **Status Code:** `204 No Content`

**Request URL Example**

```
DELETE https://jettlink-jetlink.hf.space/api/v1/urls/1
```

## Tech Stack

* **Backend:** Spring Boot / JDK 25
* **Database:** Supabase (PostgreSQL)
* **Cache:** Redis / (Upstash)
* **Hosting/Deployment:** Hugging Face Spaces (`hf.space`)
