import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-main-navbar',
  templateUrl: './main-navbar.component.html',
  styleUrls: ['./main-navbar.component.scss']
})
export class MainNavbarComponent {
  auctionForm = new FormGroup({
    title: new FormControl(''),
    category: new FormControl(''),
    image: new FormControl(''),
    description: new FormControl(''),
    startingPrice: new FormControl(''),
    endDate: new FormControl(''),
    endTime: new FormControl(''),
    userEmail: new FormControl(''),
  });

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private modalService: NgbModal) {}

  // Open modal
  openVerticallyCentered(content: any) {
    this.modalService.open(content, { centered: true });
  }
  //Add new auction
  addAuction() {
    console.log("Add new auction");
  }

}
