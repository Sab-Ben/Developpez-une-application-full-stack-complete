import { NgModule } from '@angular/core';
import { CardListComponent } from './cardList/card-list.component';
import { HeaderComponent } from './header/header.component';
import { MaterialModule } from '../material.module';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { LayoutComponent } from './layout/layout.component';

@NgModule({
  declarations: [CardListComponent, HeaderComponent, LayoutComponent],
  imports: [MaterialModule, RouterModule, CommonModule, ReactiveFormsModule],
  exports: [CardListComponent, HeaderComponent, LayoutComponent],
})
export class ComponentsModule {}
