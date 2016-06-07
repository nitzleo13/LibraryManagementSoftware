USE [Bodhi_lbms]
GO

/****** Object:  Table [dbo].[book_loans]    Script Date: 11/3/2014 7:20:06 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[book_loans](
	[loan_id] [varchar](50) NOT NULL,
	[book_id] [varchar](50) NOT NULL,
	[branch_id] [varchar](50) NOT NULL,
	[card_no] [int] NOT NULL,
	[date_out] [date] NOT NULL,
	[due_date] [date] NOT NULL,
	[date_in] [date] NULL,
 CONSTRAINT [PK_book_loans] PRIMARY KEY CLUSTERED 
(
	[loan_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[book_loans]  WITH NOCHECK ADD  CONSTRAINT [FK_book_loans_book_loans] FOREIGN KEY([branch_id])
REFERENCES [dbo].[library_branch] ([branch_id])
GO

ALTER TABLE [dbo].[book_loans] CHECK CONSTRAINT [FK_book_loans_book_loans]
GO

ALTER TABLE [dbo].[book_loans]  WITH NOCHECK ADD  CONSTRAINT [FK_book_loans_Books] FOREIGN KEY([book_id])
REFERENCES [dbo].[Books] ([book_id])
GO

ALTER TABLE [dbo].[book_loans] CHECK CONSTRAINT [FK_book_loans_Books]
GO

ALTER TABLE [dbo].[book_loans]  WITH NOCHECK ADD  CONSTRAINT [FK_book_loans_borrower] FOREIGN KEY([card_no])
REFERENCES [dbo].[borrower] ([card_no])
GO

ALTER TABLE [dbo].[book_loans] CHECK CONSTRAINT [FK_book_loans_borrower]
GO


