import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
// kaydimari liya l'application diali

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
