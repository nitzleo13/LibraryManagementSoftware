USE [Bodhi_lbms]
GO

/****** Object:  Table [dbo].[book_copies]    Script Date: 11/3/2014 7:20:19 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[book_copies](
	[book_id] [varchar](50) NOT NULL,
	[branch_id] [varchar](50) NOT NULL,
	[no_of_copies] [int] NOT NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[book_copies]  WITH NOCHECK ADD  CONSTRAINT [FK_book_copies_Books] FOREIGN KEY([book_id])
REFERENCES [dbo].[Books] ([book_id])
GO

ALTER TABLE [dbo].[book_copies] CHECK CONSTRAINT [FK_book_copies_Books]
GO

ALTER TABLE [dbo].[book_copies]  WITH CHECK ADD  CONSTRAINT [FK_book_copies_library_branch] FOREIGN KEY([branch_id])
REFERENCES [dbo].[library_branch] ([branch_id])
GO

ALTER TABLE [dbo].[book_copies] CHECK CONSTRAINT [FK_book_copies_library_branch]
GO


