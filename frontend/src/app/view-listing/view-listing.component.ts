import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Form, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Params } from '@angular/router';
import { first, map } from 'rxjs';
import { AuthenticationService } from '../authentication.service';
import { AuctionItem } from '../model';

@Component({
  selector: 'app-view-listing',
  templateUrl: './view-listing.component.html',
  styleUrls: ['./view-listing.component.scss']
})
export class ViewListingComponent implements OnInit {
  bidFormControl: FormControl = new FormControl('');
  myParam: string;
  auctionItem: AuctionItem;

  loading = false;
  submitted = false;

  currentUser: any;

  constructor(private route: ActivatedRoute, private httpClient: HttpClient, private authentificationService: AuthenticationService) { }

  ngOnInit(): void {
    this.currentUser = this.authentificationService.currentUserValue;
    this.route.params.subscribe((params: Params) => this.myParam = params['id']);

    this.getListing();
  }

  getListing() {
    this.httpClient.get<AuctionItem>('http://localhost:8080/product/'+this.myParam)
      .subscribe((data) => {
        this.auctionItem = data;
      });
  }

  bidPost(price: number) {
      return this.httpClient.post<any>(`http://localhost:8080/bid`, { userEmail: this.currentUser.email, idProduct: this.auctionItem.id, price: price })
      .pipe(map(user => {
        this.authentificationService.reloadPage();
        return user;
      }));
  }

  bid() {
    this.submitted = true;

    this.loading = true;
    this.bidPost(this.bidFormControl.value)
    .pipe(first())
    .subscribe(
      (data) => {
      },
      (error) => {
        this.loading = false;
      }
    );
    this.loading = false;
    //this.authentificationService.reloadPage();
  }
}
