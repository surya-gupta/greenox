import { NgModule } from "@angular/core";
import { MatButtonModule, MatGridListModule, MatCardModule, MatTabsModule } from "@angular/material"
import { MatChipsModule } from '@angular/material/chips';
import { MatBadgeModule } from '@angular/material/badge';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';



@NgModule({
    imports: [MatButtonModule, MatGridListModule, MatCardModule, MatChipsModule, MatTabsModule, MatBadgeModule, MatInputModule, MatFormFieldModule, MatSelectModule],
    exports: [MatButtonModule, MatGridListModule, MatCardModule, MatChipsModule, MatTabsModule, MatBadgeModule, MatInputModule, MatFormFieldModule, MatSelectModule]
})

export class MaterialModule {
}
