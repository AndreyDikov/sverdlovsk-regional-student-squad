import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { SnippetComponent } from './snippet/snippet.component';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {MatTooltipModule} from '@angular/material/tooltip';



@NgModule({
  declarations: [
    SnippetComponent
  ],
  imports: [
    CommonModule,
    NgOptimizedImage,
    RouterLink,
    RouterLinkActive,
    MatTooltipModule
  ],
  exports: [
    SnippetComponent
  ]
})
export class SharedModule { }
