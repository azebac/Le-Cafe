import { Routes, RouterModule } from '@angular/router';
//import { AuthGuard } from './_guards';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';

// Aqui es donde nos encargamos de las asociaciones pagina-vinculo
const appRoutes: Routes = [
    { path: '', component: HomeComponent},
    //{ path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'registro', component: RegisterComponent },

    // Si el vinculo solicitado no existe lo redirecciono al home
    { path: '**', redirectTo: '' }
];

export const Routing = RouterModule.forRoot(appRoutes);