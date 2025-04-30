[![Tests](https://github.com/otto-ifak/aas-business-card/actions/workflows/ci.yml/badge.svg)](https://github.com/otto-ifak/aas-business-card/actions/workflows/ci.yml)

# AAS Business Card

**Supercharge Your Business Cards with the Power of Digitalization**  

Why settle for static contact details when you can embrace the future of information exchange?  
Transform your business card into a smart, machine-readable gateway using the **Asset Administration Shell (AAS)** — a leading standard of Industry 4.0.  
Here’s how to elevate your contact sharing:  

1. **Serve Your Digital Identity**  
   Publish your contact details using an AAS Submodel Service, based on the established Submodel Template "Contact Information".

2. **Generate an Asset Link**  
   Create an **Asset Link** pointing to your AAS contact submodel, and render it as a **QR code**.  

3. **Print and Impress**  
   Embed the QR code onto your business card. Now, anyone can scan it to instantly access your complete contact information — digitally structured, semantically rich, and fully aligned with the AAS framework.  

<p align="center">
   <img src="doc/screenshot.png" alt="AAS Business Card" width="300"/>
</p>

Welcome to the next generation of networking!

**Features**:
* Edit the available contact information
* Render whole business cards ready for printing
* Render single QR codes in case you want to use your own layout
* Serve the information via an AAS Submodel Service
* Supports multiple users in parallel

## Setup
Please refer to `compose.prod.yml` for an example.
Update `POSTGRES_PASSWORD` and `APP_PASSWORD` or provide an `.env` file.
Then you can start the server using:
```sh
docker compose -f compose.prod.yml up
```
Finally visit http://localhost:8080.

## Development

Start container:
```sh
docker compose up
```

Build Java Script:
```sh
docker compose exec frontend bash
npm run build
```
Alternatively run `npm run watch` for auto-rebuild

Start Server

```sh
docker compose exec backend bash
./gradlew booRun
```

Then visit http://localhost:8080.

Alternatively, start server in continuous mode:
```sh
./gradlew build --continuous
```
Then in another terminal:
```sh
./gradlew bootRun
```
