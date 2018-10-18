import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryViewComponent } from './view.component';

describe('InventoryViewComponent', () => {
  let component: InventoryViewComponent;
  let fixture: ComponentFixture<InventoryViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InventoryViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InventoryViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
