import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { first, map, shareReplay } from 'rxjs/operators';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthenticationService } from '../authentication.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-main-navbar',
  templateUrl: './main-navbar.component.html',
  styleUrls: ['./main-navbar.component.scss']
})
export class MainNavbarComponent implements OnInit {
  auctionForm: FormGroup;
  loading = false;
  submitted = false;

  currentUser: any;

  public base64textString: any = "";

  constructor(private modalService: NgbModal, private authentificationService: AuthenticationService, private http: HttpClient) {}

  ngOnInit(): void {
    this.currentUser = this.authentificationService.currentUserValue;

    this.auctionForm = new FormGroup({
      title: new FormControl(''),
      category: new FormControl(''),
      image: new FormControl(''),
      description: new FormControl(''),
      startingPrice: new FormControl(''),
      endDate: new FormControl(''),
      endTime: new FormControl(''),
      userEmail: new FormControl(''),
    });
    const current = new Date();
  }

  logoutCurrentUser() {
    this.authentificationService.logout();
    this.authentificationService.reloadPage();
  }

  // Open modal
  openVerticallyCentered(content: any) {
    this.modalService.open(content, { centered: true });
  }
  //Add new auction
  addAuction() {
    this.submitted = true;

    if (this.auctionForm.invalid) {
      return;
    }

    this.loading = true;
    const endDateTime = new Date(this.f['endDate'].value + " " + this.f['endTime'].value);
    this.postNewAuction(this.f['title'].value, this.f['category'].value, this.base64textString, this.f['description'].value, this.f['startingPrice'].value, endDateTime.toISOString(), this.currentUser.email)
    .pipe(first())
    .subscribe(
      (data) => {
      },
      (error) => {
        this.loading = false;
      }
    );
    this.loading = false;
  }

  get f() {
    return this.auctionForm.controls;
  }

  handleFileSelect(evt){
      var files = evt.target.files;
      var file = files[0];

    if (files && file) {
        var reader = new FileReader();
        reader.onload =this._handleReaderLoaded.bind(this);
        reader.readAsBinaryString(file);
    }
  }

  _handleReaderLoaded(readerEvt) {
      var binaryString = readerEvt.target.result;
      this.base64textString= btoa(binaryString);
      console.log(btoa(binaryString));
  }

  postNewAuction(title: string, category: string, image: string, description: string, startingPrice: number, endTime: string, userEmail: string) {
    var images = [];
    images.push(image);
    return this.http.post<any>(`http://localhost:8080/product`, { title: title, category: category, images: images, description: description, startingPrice: startingPrice, endTime: endTime, userEmail: userEmail })
            .pipe(map(user => {
              this.authentificationService.reloadPage();
              return user;
            }));
  }

}
