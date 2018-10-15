import { NgModule } from "@angular/core";
import { MatButtonModule, MatGridListModule, MatCardModule,MatTabsModule } from "@angular/material"
import {MatChipsModule} from '@angular/material/chips';

@NgModule({
    imports: [MatButtonModule, MatGridListModule, MatCardModule, MatChipsModule,MatTabsModule],
    exports: [MatButtonModule, MatGridListModule, MatCardModule, MatChipsModule,MatTabsModule]
})

export class MaterialModule {
}
