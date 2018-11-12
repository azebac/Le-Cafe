import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AutentificacionService } from '../services/autentificacion/autentificacion.service';



@Injectable()
//Esta clase intercepta las comunicaciones y les agrega el header de la autentificacion
export class JwtInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AutentificacionService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let currentUser = this.authenticationService.currentUserValue;
        if (currentUser && currentUser.token) {
            request = request.clone({
                setHeaders: { 
                    Authorization: `Bearer ${currentUser.token}`
                }
            });
        }

        return next.handle(request);
    }
}