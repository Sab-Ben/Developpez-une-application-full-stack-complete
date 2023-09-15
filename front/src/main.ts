import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic()
  .bootstrapModule(AppModule)
  .catch((err) => console.error(err));

//TODO : when i try to connect on auth page while not connected, blank page ??
//TODO : Mettre le active link en violet dans le header
//TODO : Refaire le circle background pour le user dans le menu déroulant
//TODO : Refactor
