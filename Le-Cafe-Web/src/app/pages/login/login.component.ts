import { Component, OnInit, ElementRef, AfterViewInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Usuario } from 'src/app/models/usuario';
import { ActivatedRoute, Router } from '@angular/router';
import { AutentificacionService } from 'src/app/services/autentificacion/autentificacion.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, AfterViewInit {

  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  usuario: Usuario = new Usuario();

  constructor(private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private elementRef: ElementRef,
    private authenticationService: AutentificacionService) {
    //Si ya existe un usuario logeado no hace falta cargar el resto del modulo, lo redirecciono a home
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    //Instancio el formulario de login
    this.loginForm = this.formBuilder.group({
      correo: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngAfterViewInit() {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = '#f3f3f4';
  }

  /**
   * Getter del formulario para poder acceder facil a sus controles para ver los errores
   */
  get formulario() { return this.loginForm.controls; }

  /**
   * Metodo que se dispara cuando el usuario hace login
   */
  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.usuario.correo = this.formulario.correo.value;
    this.usuario.password = this.formulario.password.value;
    this.authenticationService.login(this.usuario)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(["/home"]);
        },
        error => {
          //this.alertService.error(error);
          this.loading = false;
        });
  }

}
