# Environment variables

No credential is committed to this repository. Every secret is read from the
environment at startup, and the application **refuses to start** if a required
one is missing — that is deliberate, so a misconfigured deployment fails loudly
instead of running with a fallback nobody intended.

## Required

| Variable | Used by | Notes |
|---|---|---|
| `JWT_SECRET` | `JwtUtil` — signs and verifies JWTs | **32+ characters.** HS256 needs a 256-bit key; a shorter value makes `Keys.hmacShaKeyFor` throw at startup. Changing it invalidates every token already issued |
| `SPRING_DATA_MONGODB_URI` | Spring Data MongoDB | Full connection string. Percent-encode reserved characters in the password: `@` → `%40`, `/` → `%2F`, `:` → `%3A`. Falls back to `mongodb://localhost:27017/journaldb` when unset |
| `SPRING_MAIL_USERNAME` | `EmailService` | Gmail address used as the SMTP sender |
| `SPRING_MAIL_PASSWORD` | `EmailService` | Gmail **app password**, not the account password |
| `GOOGLE_CLIENT_ID` | Google OAuth2 login | From the Google Cloud console, OAuth 2.0 client |
| `GOOGLE_CLIENT_SECRET` | Google OAuth2 login | Same client. Treat as a secret |
| `WEATHER_API_KEY` | `WeatherService` | WeatherStack API key |

Redis is expected on `localhost:6379` and is configured in `application.yml`
rather than the environment, since it holds no credential.

## Generating a JWT secret

PowerShell:

```powershell
$b=[byte[]]::new(24); [Security.Cryptography.RandomNumberGenerator]::Create().GetBytes($b); [BitConverter]::ToString($b).Replace('-','').ToLower()
```

Bash:

```bash
openssl rand -hex 24
```

Both produce 48 hexadecimal characters. Hex avoids the `+` and `/` that Base64
produces, which would otherwise need percent-encoding wherever the value ends up
inside a URI.

## Setting them

**Locally (IntelliJ):** Run → Edit Configurations → Environment variables. The
resulting `.run/*.run.xml` holds these values in cleartext and is gitignored —
keep it that way.

**Locally (shell):**

```bash
export JWT_SECRET=...
export SPRING_DATA_MONGODB_URI=...
```

**Deployed:** use the platform's secret store rather than a file on disk —
Kubernetes Secrets, AWS Secrets Manager, Azure Key Vault, or the host's
environment configuration.

## Tests

`src/test/resources/application-test.yml` supplies defaults for every variable
so the test context boots with nothing set. Those values are placeholders and
are not credentials.

## If a secret is exposed

Rotate first, then clean up. A credential that has been pushed is compromised
from the moment it lands, and removing it from the current commit does not
remove it from the history or from anything that has already cloned the repo.
