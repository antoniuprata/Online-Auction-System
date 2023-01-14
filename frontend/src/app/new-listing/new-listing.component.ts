import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-new-listing',
  standalone: true,
  templateUrl: './new-listing.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./new-listing.component.scss']

})
export class NewListingComponent implements OnInit {

    constructor(private modalService: NgbModal) {}

    ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

    openBackDropCustomClass(content: any) {
      this.modalService.open(content, { backdropClass: 'light-blue-backdrop' });
    }

    openWindowCustomClass(content: any) {
      this.modalService.open(content, { windowClass: 'dark-modal' });
    }

    openSm(content: any) {
      this.modalService.open(content, { size: 'sm' });
    }

    openLg(content: any) {
      this.modalService.open(content, { size: 'lg' });
    }

    openXl(content: any) {
      this.modalService.open(content, { size: 'xl' });
    }

    openFullscreen(content: any) {
      this.modalService.open(content, { fullscreen: true });
    }

    openVerticallyCentered(content: any) {
      this.modalService.open(content, { centered: true });
    }

    openScrollableContent(longContent: any) {
      this.modalService.open(longContent, { scrollable: true });
    }

    openModalDialogCustomClass(content: any) {
      this.modalService.open(content, { modalDialogClass: 'dark-modal' });
    }
  }

