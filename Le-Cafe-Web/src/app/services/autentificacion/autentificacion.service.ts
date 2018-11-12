import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Usuario } from '../../models/usuario';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AutentificacionService {
  private currentUserSubject: BehaviorSubject<Usuario>;
  public currentUser: Observable<Usuario>;
  constructor(private http: HttpClient) {
    // Obtengo el usuario en memoria, si no existe sera null y ya
    this.currentUserSubject = new BehaviorSubject<Usuario>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  /**
   * Getter para el usuario actual
   */
  public get currentUserValue(): Usuario {
    return this.currentUserSubject.value;
  }

  /**
   * Metodo para realizar login hacia el backend
   * @param usuario Objeto usuario a mandar hacia el backend
   */
  login(usuario: Usuario) {
    return this.http.post<any>(`${environment.apiUrl}/usuario/autenticar`, usuario)
      .pipe(map(user => {
        // Si tengo usuario y tengo token
        if (user && user.token) {
          // El usuario se guarda en memoria
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.currentUserSubject.next(user);
        }

        return user;
      }));
  }
}
