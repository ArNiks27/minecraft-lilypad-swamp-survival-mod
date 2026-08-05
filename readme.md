# Minecraft Lilypad Core Mechanics Mod (Dynamic Progression & Entity Loops)

An advanced, systems-driven Minecraft modification created for the popular YouTube channel **Burenochek** (800k+ subscribers). This project focuses on heavy gameplay logic, custom tile entities, and unique mob interaction loops designed to drive progression on a highly restrictive survival map.

## Project Showcase & Proof of Work
* **Watch full gameplay video:** [200 Days Surviving on a Lilypad](https://youtu.be)
* **My role:** core Gameplay Programmer / Systems & Interaction Developer & MODELING AND TEXTURING CATTAIL AND FLOWERS.
* **Production status:** released and fully optimized for heavy video production workflows.


[![Watch the Video](https://i.ytimg.com/vi/iSVBkQrnVCg/hqdefault.jpg)](https://youtu.be/iSVBkQrnVCg?si=mLIRi9FWsQWPbDbQ)
---

## 🛠 Features Implemented

### 1. Dynamic Platform Mechanics & RNG Survival Logic
Designed and programmed the core "Lilypad Economy" that dictates player progression and movement:
* **The Squid game platform logic:** implemented an RNG system for deployed custom seeds. The code generates either a *Stable* or *Weak* lilypad variant. While visually identical, stepping on a Weak platform triggers an immediate block collapse, forcing the player into the environmental mud obstacle.
* **Automated seed generation:** coded a custom tile entity within the core Lilypad block that spawns interactive flowers on a strict 3-minute global tick timer.
* **Platform stabilization filter:** programmed a unique interaction with Kelp items. Applying Kelp permanently stabilizes any platform, lifting the single-block weight limit and enabling modular base building.
* **Custom plant cattail:** cattail inflorescence and sticks with crafts. Balanced for survival game 

---

## Tech Stack 
* **Language:** Java
* **Framework:** Forge API 1.20.1
* **Key Achievements:**
    * Successfully separated complex gameplay logic from heavy map-dependent environmental elements (fog/mud assets).
    * Optimized real-time block-state updates during rapid fluid extraction and platform stabilization.
    * Resolved complex tick conflicts during rapid multi-entity interactions within automated mob traps.
    * ДА ЧТОБЫ РАБОТАЛО ВСЕ НОРМАЛЬНО, ВЫ НЕ ПРЕДСТАВЛЯЕТЕ КАК Я УСТАЛ ПРАВКИ БЕСКОНЕЧНЫЕ ДЕЛАТЬ + ЕЩЕ КАКИЕ-ТО НОВОВЕДЕНИЯ 
