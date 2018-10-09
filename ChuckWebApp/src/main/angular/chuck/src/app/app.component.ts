import { Component, Input } from '@angular/core';

import { ChuckService } from './chuck/chuck.service'
import { Chuck } from './chuck/chuck.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  title = 'Chuck';
  @Input() joke: string = 'Loading...';

  constructor(private chuckService: ChuckService) {
    this.chuckService.getRandomJoke().subscribe((chuck: Chuck) => {
      console.log("Received Chuck: " + chuck);
      this.joke = chuck.joke;
    });
  }
}
