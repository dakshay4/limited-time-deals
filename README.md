You happen to be a budding entrepreneur and you have come up with an idea to build an e-commerce giant like Amazon, Flipkart, Walmart, etc. As part of this ambition, you want to build a platform to duplicate the concept of Limited Time Deals. note that you are building this feature on top of already built product.

Limited Time Deals
*******************
A limited-time deal implies that a seller will put up an item on sale for a limited time period, say, 2 hours, and will keep a maximum limit on the number of items that would be sold as part of that deal.
Users cannot buy the deal if the deal time is over
Users cannot buy if the maximum allowed deal has already been bought by other users.
One user cannot buy more than one item as part of the deal.
There are multiple ways by which a deal can be claimed. Use appropriate design patterns to switch to a certain way of claiming a deal at runtime

The task is to create APIs to enable the following operations (Donot create api, just create method and take appropriate request and response params)
1) Create a deal with the price and number of items to be sold as part of the deal - Required
2) End a deal
3) Update a deal to increase the number of items or end time
4) Claim a deal - Required

Expectations
************
Code should be modular and working - IMP
Code should follow Open closed principle - IMP
Use design patterns whenever required - IMP
Use local storage(in memory data structures) instead of database
Throw appropriate exceptions whever necessary
Use appropriate variable naming
Classes and their relationships should be proper while doing entity modelling
Separation of concerns should be used whenver required
Test classes - IMP (at least driver code is needed)
Use concurrency wherever required - IMP

HLD

API
createDeal(itemId, qty, endTime);
endDeal(itemId)
update(dealId, qty, endTime)
claimDeal(userId, itemId, dealId)


DB Schema -
User(id, name, phoneNo, email, createdAt)
Item (id, name, createdAt)
Deal (id, itemId, quantity, endTime, createdAt)
DealTransaction (id, userId, dealId, createdAt)


Assumptions - 
1 Deal can have not more than 5000 items in the time period.

Data Storage -
Map<dealId, List<DealTransaction>> 

One of the Purchase Strategy ->
Deal.getCreatedAt + deal.endTimeInMinutes (60*1000) <= System.currentTimeMillis (TRUE)

It will give me count of items purchased in the deal.
Also, Iterate over List and find if the userId requesting the deal, has already bought the item from the deal.
if count is less than Deal.getQuantity() && userLevelDealTransaction == null => Create Transaction


Classes -
Generalisation :
OneTimePurchaseStrategy implements ClaimDealStrategy

Exceptions - 
User has already Claimed the deal
Deal has been ended
Deal Item is Out of Stock.

