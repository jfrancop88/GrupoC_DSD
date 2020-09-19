import { Component, } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { Activas , Archivadas, Busqueda } from '../service/model.repository';

@Component({
  moduleId: module.id,
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent {
  fullScreen: boolean = false;
  constructor(
      private repoActivas: Activas,
      private repoArchivadas: Archivadas,
      private repoBusqueda: Busqueda,
      private auth: AuthService,
      private router: Router) {}

  logout() {
      this.auth.clear();
      this.router.navigateByUrl('/signin');

      // do repo reset
      this.repoActivas.models = [];
      this.repoActivas.amount = 0;
      this.repoArchivadas.models = [];
      this.repoArchivadas.amount = 0;
      this.repoBusqueda.models = [];
      this.repoBusqueda.amount = 0;
  }

  doFullScreen() {

      const element = document.getElementById('wrapper');

      function launchIntoFullscreen(element) {
          if (element.requestFullscreen) {
              element.requestFullscreen();
          } else if (element.mozRequestFullScreen) {
              element.mozRequestFullScreen();
          } else if (element.webkitRequestFullscreen) {
              element.webkitRequestFullscreen();
          } else if (element.msRequestFullscreen) {
              element.msRequestFullscreen();
          }
      }
      function exitFullscreen() {
          if (document.exitFullscreen) {
              document.exitFullscreen();
          } /*else if (document.mozCancelFullScreen) { //Firefox
              document.mozCancelFullScreen();
          } else if (document.webkitExitFullscreen) { // Chrome, Safari and Opera
              document.webkitExitFullscreen();
          } */
      }

      if (this.fullScreen) {
          exitFullscreen();
          this.fullScreen = false;
      } else {
          launchIntoFullscreen(element);
          this.fullScreen = true;
      }

  }
}
