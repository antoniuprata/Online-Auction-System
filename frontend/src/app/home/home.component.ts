import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { Component, OnInit } from '@angular/core';
declare var anime: any;

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

  ngAfterViewInit(): void {
    // Wrap every letter in a span
    const textWrapper1 = document.querySelector('.an-1');
    textWrapper1!.innerHTML = textWrapper1!.textContent!.replace(/\S/g, "<span class='letter'>$&</span>");

    const textWrapper2 = document.querySelector('.an-2');
    textWrapper2!.innerHTML = textWrapper2!.textContent!.replace(/\S/g, "<span class='letter'>$&</span>");

    anime.timeline({loop: true})
      .add({
        targets: '.an-1 .letter',
        opacity: [0,1],
        easing: "easeInOutQuad",
        duration: 2250,
        delay: (_el: any, i:any) => 70 * (i+1)
      }).add({
        targets: '.an-1',
        opacity: 0,
        duration: 1000,
        easing: "easeOutExpo",
        delay: 100
      });

      anime.timeline({loop: true})
      .add({
        targets: '.an-2 .letter',
        opacity: [0,1],
        easing: "easeInOutQuad",
        duration: 2250,
        delay: (_el: any, i:any) => 90 * (i+1)
      }).add({
        targets: '.an-2',
        opacity: 0,
        duration: 1000,
        easing: "easeOutExpo",
        delay: 100
      });

}

  ngOnInit(): void {
    this.isShown = true;
  }

}
