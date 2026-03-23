# Environment Variables

Sensitive values must be provided with environment variables and must not be committed to repository config files.

## Required Variables

- `MONGODB_URI` (required in `prod`; optional in `dev`, which defaults to `mongodb://localhost:27017/journaldb`)

## PowerShell Example

```powershell
$env:MONGODB_URI="mongodb+srv://<user>:<password>@<cluster>/<db>?retryWrites=true&w=majority"
```

## Linux/macOS Example

```bash
export MONGODB_URI="mongodb+srv://<user>:<password>@<cluster>/<db>?retryWrites=true&w=majority"
```
