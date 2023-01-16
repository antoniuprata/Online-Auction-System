import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { HowItWorksComponent } from './how-it-works/how-it-works.component';
import { BrowseProductsComponent } from './browse-products/browse-products.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { BrowseSculpturesComponent } from './browse-sculptures/browse-sculptures.component';
import { BrowsePaintingsComponent } from './browse-paintings/browse-paintings.component';
import { ViewListingComponent } from './view-listing/view-listing.component';
import { NewListingComponent } from './new-listing/new-listing.component';
import { MyAuctionsComponent } from './my-auctions/my-auctions.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import { AccountSettingsComponent } from './account-settings/account-settings.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'about-us', component: AboutUsComponent },
  { path: 'how-it-works', component: HowItWorksComponent },
  { path: 'browse-products', component: BrowseProductsComponent },
  { path: 'browse-paintings', component: BrowsePaintingsComponent },
  { path: 'browse-sculptures', component: BrowseSculpturesComponent },
  { path: 'login-form', component: LoginFormComponent },
  { path: 'register-form', component: RegisterFormComponent },
  { path: 'view-listing', component: ViewListingComponent },
  { path: 'new-listing', component:  NewListingComponent },
  { path: 'my-auctions', component:  MyAuctionsComponent },
  { path: 'watchlist', component: WatchlistComponent},
  { path: 'account-settings', component: AccountSettingsComponent}
];

@NgModule({
  declarations: [],
  imports: [CommonModule, RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
