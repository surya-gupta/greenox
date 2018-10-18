import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryUpdateComponent } from './update.component';

describe('InventoryUpdateComponent', () => {
  let component: InventoryUpdateComponent;
  let fixture: ComponentFixture<InventoryUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InventoryUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InventoryUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
