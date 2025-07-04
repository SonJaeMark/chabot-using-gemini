
# 🤖 Gemini-Powered Chatbot (Spring Boot)

This project is a **Spring Boot RESTful API** that integrates with **Google's Gemini API** to provide AI-powered conversation, with memory support across chat sessions. You can upload context (like a dataset) and chat intelligently with Gemini using that context.

---

## 🚀 Features

- 🧠 Automatic session-based memory
- 📝 Upload structured context (e.g. JSON array of data)
- 💬 Continue conversation using `sessionId`
- 🔁 Reset chat session anytime
- 🌐 RESTful endpoints (chat, upload, reset)

---

## 📦 Requirements

- Java 17+
- Maven
- Internet connection (to call Gemini API)
- Your own **Gemini API key** and **endpoint**

---

## 🔐 Setup Your API Credentials

The application uses two environment-based configs:

| Property             | Description                       |
|----------------------|-----------------------------------|
| `gemini.api.key`     | Your Gemini API key from Google   |
| `gemini.api.endpoint`| The base URL (e.g. `https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent`) |

### 🛠️ How to provide these:

Create a file named `application.properties` under:
```
src/main/resources/application.properties
```

Then add:

```properties
gemini.api.key=YOUR_GEMINI_API_KEY
gemini.api.endpoint=https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent
```

**⚠️ Never commit this file. It's ignored via `.gitignore`.**

---

## 📬 API Endpoints

### ▶️ `POST /gemini/upload-context`

Uploads context (like a JSON array) to feed Gemini for future questions.

- **Body**: Raw JSON (Content-Type: `application/json`)
- **Optional param**: `sessionId`
- **Returns**: `sessionId` (auto-generated if not provided)

📦 Example payload:
```json
[
  {
    "title": "The Galactic Escape",
    "ticket_price": 350.00,
    "cinema": "SM Megamall Cinema 4"
  }
]
```

---

### 💬 `POST /gemini/chat`

Sends a message to Gemini, with full memory from `sessionId`.

- **Params**:
  - `prompt`: your question
  - `sessionId`: (required if using uploaded context)
- **Returns**: Gemini’s reply (and maintains memory)

📦 Example:
```http
POST /gemini/chat?prompt=How much is The Galactic Escape?&sessionId=abc123
```

---

### 🔁 `POST /gemini/reset`

Resets the memory for a session.

- **Params**: `sessionId`

📦 Example:
```http
POST /gemini/reset?sessionId=abc123
```

---

## 🧪 Postman Usage

> To test easily, import the endpoints into Postman:
- Set body type to `raw` → `JSON`
- Add header: `Content-Type: application/json`
- Use the returned `sessionId` between calls

---

## 🧠 How Memory Works

- Each session (via `sessionId`) stores:
  - User prompts
  - Gemini responses
- Stored in memory (`Map<String, List<...>>`)
- All previous turns are sent in every request to Gemini

---

## 📁 Project Structure

```bash
📁 gemini-powered-chatbot
├── src/
│   └── main/
│       ├── java/dev/mark/gemini_powered_chatbot/
│       │   ├── controller/     # REST endpoints
│       │   ├── service/        # Gemini + memory logic
│       │   └── service/chat/   # Session memory manager
│       └── resources/
│           └── application.properties (create this!)
├── .gitignore
├── pom.xml
└── README.md
```

---

## 🧳 Deployment

You can run the app locally:
```bash
mvn spring-boot:run
```

Or build the JAR:
```bash
mvn clean package
java -jar target/gemini-powered-chatbot-0.0.1-SNAPSHOT.jar
```

---

## 🐳 Run with Dev Container (Recommended)

This project supports [Dev Containers](https://containers.dev/) for a consistent and isolated development environment using Docker and VS Code.

### ✅ Requirements

- [Docker](https://www.docker.com/)
- [Visual Studio Code](https://code.visualstudio.com/)
- [Dev Containers Extension](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)

---

### 🚀 How to Run in Dev Container

1. **Clone this repository**
   ```bash
   git clone https://github.com/your-username/gemini-powered-chatbot.git
   cd gemini-powered-chatbot
   ```

2. **Open in VS Code**

3. **Reopen in Container**
   - Press `F1` → “Dev Containers: Reopen in Container”
   - VS Code will build and open the project inside the container

4. **Add your API credentials**
   - Create `src/main/resources/application.properties` with:
     ```properties
     gemini.api.key=YOUR_GEMINI_API_KEY
     gemini.api.endpoint=https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent
     ```

---

### 🧪 Test It

Once inside the dev container, run:
```bash
mvn spring-boot:run
```

Your API will be available at:
```
http://localhost:8080
```

---

## 📌 License

This project is open-source for educational use.

---

## 🙋 Need Help?

Feel free to open an issue or contact the author.

---