import { NgModule } from "@angular/core";
import { MatButtonModule, MatGridListModule, MatCardModule, MatTabsModule } from "@angular/material"
import { MatChipsModule } from '@angular/material/chips';
import { MatBadgeModule } from '@angular/material/badge';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatRippleModule } from '@angular/material/core';
import { MatTooltipModule } from '@angular/material/tooltip';

@NgModule({
    imports: [MatPaginatorModule, MatTableModule, MatButtonModule, MatGridListModule, MatCardModule, MatChipsModule, MatTabsModule, MatBadgeModule, MatInputModule, MatFormFieldModule, MatSelectModule, MatDialogModule, MatSnackBarModule, MatRippleModule, MatTooltipModule],
    exports: [MatPaginatorModule, MatTableModule, MatButtonModule, MatGridListModule, MatCardModule, MatChipsModule, MatTabsModule, MatBadgeModule, MatInputModule, MatFormFieldModule, MatSelectModule, MatDialogModule, MatSnackBarModule, MatRippleModule, MatTooltipModule]
})

export class MaterialModule {
}
