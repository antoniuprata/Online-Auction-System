import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { Component, OnInit } from '@angular/core';

const fadeInOut = trigger('fadeInOut', [
  state(
    'in',
    style({
      opacity: 1,
    })
  ),
  transition('void => *', [style({ opacity: 0 }), animate('2s ease-out')]),
  transition('* => void', [animate('1s ease-out'), style({ opacity: 0 })]),
]);

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  animations: [fadeInOut],
})
export class HomeComponent implements OnInit {
  isShown = false;

  constructor() { }

  ngOnInit(): void {
    this.isShown = true;
  }

}
